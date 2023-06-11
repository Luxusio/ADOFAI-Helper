package io.luxus.adofai.lib.json

import io.luxus.adofai.lib.util.readChar
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.util.*
import kotlin.collections.ArrayDeque

/**
 * this class reads broken adofai-style json file and returns a valid json character stream
 * this class is not thread-safe
 * @param inputStream input stream of adofai-style json file
 */
class AdofaiJsonInputStream(
    inputStream: InputStream
) : InputStream() {

    private val charset = Charsets.UTF_8
    private val reader = InputStreamReader(inputStream, charset)
    private val charBuffer = CharBuffer.allocate(1)
    private val byteBuffer = ByteBuffer.allocate(4)
    private val encoder = charset.newEncoder()

    private var charQueue = ArrayDeque<Int>()
    private var isListStack = Stack<Boolean>()
    private var hasPrevObject = false
    private var keyMode = true
    private var previousCharacter: Char? = null


    override fun read(): Int {
        if (charQueue.isEmpty()) {
            fillStack()
        }

        return charQueue.removeFirstOrNull() ?: -1
    }

    private fun fillStack() {
        val c: Char = tryGetPreviousCharacter() ?: reader.readChar() ?: return

        when (c) {
            '{', '[' -> {
                if (hasPrevObject && keyMode) {
                    appendCharQueue(',')
                }
                isListStack.push(c == '[')
                appendCharQueue(c)
                hasPrevObject = false
                keyMode = true
            }

            ':' -> {
                keyMode = false
                appendCharQueue(c)
            }

            '}', ']' -> {
                isListStack.pop()
                hasPrevObject = true
                keyMode = true
                appendCharQueue(c)
            }

            '"' -> {
                // write a string value
                if (hasPrevObject && (keyMode || isListStack.peek())) {
                    appendCharQueue(',')
                }
                var escape = false
                appendCharQueue(c)

                while (true) {
                    val strC: Char = reader.readChar() ?: break

                    appendCharQueue(strC)
                    escape = if (strC == '\\') {
                        !escape
                    } else if (!escape && strC == '"') {
                        break // string end
                    } else {
                        false
                    }
                }

                hasPrevObject = true
                keyMode = !keyMode
            }

            ',', ' ', '\t', '\n', '\r' -> {
                fillStack()
            }

            else -> {
                if (isListStack.empty()) {
                    fillStack()
                    return
                }

                // write a value
                if (hasPrevObject && (keyMode || isListStack.peek())) {
                    appendCharQueue(',')
                }
                appendCharQueue(c)

                while (true) {
                    val valC = reader.readChar() ?: break

                    if (valC == '{' || valC == '[' || valC == '}' || valC == ']' || valC == ',' || valC == ' ' || valC == '\t' || valC == '\n') {
                        previousCharacter = valC
                        break
                    }
                    appendCharQueue(valC)
                }
                hasPrevObject = true
                keyMode = !keyMode
            }
        }
    }

    private fun tryGetPreviousCharacter(): Char? {
        val temp = previousCharacter
        previousCharacter = null
        return temp
    }

    private fun appendCharQueue(c: Char) {
        charBuffer.clear()
        charBuffer.put(c)
        charBuffer.flip()

        byteBuffer.clear()
        encoder.reset().encode(charBuffer, byteBuffer, true)
        encoder.flush(byteBuffer)

        for (i in 0 until byteBuffer.capacity() - byteBuffer.remaining()) {
            charQueue.addLast(byteBuffer.get(i).toInt())
        }
    }

    override fun close() {
        reader.close()
    }

}
