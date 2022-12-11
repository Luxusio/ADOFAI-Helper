package io.luxus.lib.adofai.parser.json

import io.luxus.lib.adofai.util.StringReader
import java.io.InputStream
import java.util.*


class LooseJsonParser(inputStream: InputStream) {

    companion object {
        const val BUFFER_SIZE = 1024
    }

    private val reader = StringReader(inputStream, BUFFER_SIZE)
    private var reservedChar: Char? = null


    private val sb: StringBuilder = StringBuilder()

    private val isListStack = Stack<Boolean>()
    private var hasPrevObject = false
    private var keyMode = true

    private fun hasNextChar(): Boolean {
        return reservedChar != null || reader.hasNext()
    }

    private fun nextChar(): Char {
        if (reservedChar != null) {
            val result: Char = reservedChar!!
            reservedChar = null
            return result
        }

        return reader.next()

    }

    fun nextToken(): JsonToken? {
        while (hasNextChar()) {
            val c: Char = nextChar()
            if (c == '{' || c == '[') {
                if (hasPrevObject && keyMode) {
                    sb.append(',')
                }

                val isStartArray = c == '['
                isListStack.push(isStartArray)

                sb.append(c)
                hasPrevObject = false
                keyMode = true

                return if (isStartArray) {
                    JsonToken.START_ARRAY
                } else {
                    JsonToken.START_OBJECT
                }

            } else if (c == ':') {
                keyMode = false
                sb.append(c)
            } else if (c == '}' || c == ']') {
                // todo : throw exception if met } with endArray or ] with no endArray
                val isEndArray = isListStack.pop()

                hasPrevObject = true
                keyMode = true
                sb.append(c)

                return if (isEndArray) {
                    JsonToken.END_ARRAY
                } else {
                    JsonToken.END_OBJECT
                }

            } else if (c == '"') {
                // write a string value
                if (hasPrevObject && (keyMode || isListStack.peek())) {
                    sb.append(',')
                }
                var escape = false
                sb.append(c)
                while (reader.hasNext()) {
                    val strC: Char = reader.next()
                    sb.append(strC)
                    escape = if (strC == '\\') {
                        !escape
                    } else if (!escape && strC == '"') {

                        val result = if (keyMode) {
                            JsonToken.FIELD_NAME
                        } else {
                            JsonToken.VALUE
                        }

                        hasPrevObject = true
                        keyMode = !keyMode

                        // string end
                        return result
                    } else {
                        false
                    }
                }
                hasPrevObject = true
                keyMode = !keyMode
            } else if (c != ',' && c != ' ' && c != '\t' && c != '\n') {
                // write a value
                if (hasPrevObject && (keyMode || isListStack.peek())) {
                    sb.append(',')
                }
                sb.append(c)
                while (hasNextChar()) {
                    val valC: Char = nextChar()
                    if (valC == '{' || valC == '[' || valC == '}' || valC == ']' || valC == ',' || valC == ' ' || valC == '\t' || valC == '\n') {
                        reservedChar = valC

                        return JsonToken.VALUE
                    }
                    sb.append(valC)
                }
                hasPrevObject = true
                keyMode = !keyMode
            }
        }

        return null
    }

//    fun value(): String {
//        Triple
//    }

}