package io.luxus.lib.adofai.parser.json

import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.util.*


class LooseJsonParser(private val inputStream: InputStream) {

    companion object {
        const val BUFFER_SIZE = 1024
    }

    private val buffer: CharArray = CharArray(BUFFER_SIZE)

    private val tokens: MutableList<JsonToken> = mutableListOf()

    private var idx = 0

    init {
        val jsonStr = readInputStream(inputStream)

        val sb: StringBuilder = StringBuilder(jsonStr.length)

        val isListStack = Stack<Boolean>()
        var hasPrevObject = false
        var keyMode = true

        val chars: CharArray = jsonStr.toCharArray()
        run {
            var idx = 0
            while (idx < chars.size) {
                val c: Char = chars.get(idx)
                if (c == '{' || c == '[') {
                    if (hasPrevObject && keyMode) {
                        sb.append(',')
                    }

                    val isStartArray = c == '['
                    isListStack.push(isStartArray)


                    tokens.add(
                        if (isStartArray) JsonToken.START_ARRAY
                        else JsonToken.START_OBJECT)

                    sb.append(c)
                    hasPrevObject = false
                    keyMode = true
                } else if (c == ':') {
                    keyMode = false
                    sb.append(c)
                } else if (c == '}' || c == ']') {
                    // todo : throw exception if met } with endArray or ] with no endArray
                    val isEndArray = isListStack.pop()
                    tokens.add(
                        if (isEndArray) JsonToken.END_ARRAY
                        else JsonToken.END_OBJECT)

                    hasPrevObject = true
                    keyMode = true
                    sb.append(c)
                } else if (c == '"') {
                    // write a string value
                    if (hasPrevObject && (keyMode || isListStack.peek())) {
                        sb.append(',')
                    }
                    var escape = false
                    sb.append(c)
                    idx++
                    while (idx < chars.size) {
                        val strC: Char = chars.get(idx)
                        sb.append(strC)
                        escape = if (strC == '\\') {
                            !escape
                        } else if (!escape && strC == '"') {

                            tokens.add(
                                if (keyMode) JsonToken.FIELD_NAME
                                else JsonToken.VALUE)

                            break // string end
                        } else {
                            false
                        }
                        idx++
                    }
                    hasPrevObject = true
                    keyMode = !keyMode
                } else if (c != ',' && c != ' ' && c != '\t' && c != '\n') {
                    // write a value
                    if (hasPrevObject && (keyMode || isListStack.peek())) {
                        sb.append(',')
                    }
                    sb.append(c)
                    idx++
                    while (idx < chars.size) {
                        val valC: Char = chars.get(idx)
                        if (valC == '{' || valC == '[' || valC == '}' || valC == ']' || valC == ',' || valC == ' ' || valC == '\t' || valC == '\n') {
                            idx--

                            tokens.add(JsonToken.VALUE)
                            break
                        }
                        sb.append(valC)
                        idx++
                    }
                    hasPrevObject = true
                    keyMode = !keyMode
                }
                idx++
            }
        }


    }

    private fun readInputStream(inputStream: InputStream): String {
        val bufferSize = 1024
        val buffer = CharArray(bufferSize)
        val out = java.lang.StringBuilder()
        val `in`: Reader = InputStreamReader(inputStream, Charsets.UTF_8)
        run {
            var numRead: Int
            while (`in`.read(buffer, 0, buffer.size).also { numRead = it } > 0) {
                out.append(buffer, 0, numRead)
            }
        }
        return out.toString()
    }

    fun nextToken(): JsonToken? {
        if (idx >= tokens.size) {
            return null
        }

        return tokens[idx++]
    }
}