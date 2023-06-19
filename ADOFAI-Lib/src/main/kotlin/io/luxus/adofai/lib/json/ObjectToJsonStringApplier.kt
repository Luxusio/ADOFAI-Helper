package io.luxus.adofai.lib.json

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeType
import io.luxus.adofai.lib.util.isStatic

class ObjectToJsonStringApplier {
    companion object {
        fun apply(
            source: Any,
            predicate: (String) -> Boolean = { true },
            consumer: (String) -> Unit
        ) {
            val sb = StringBuilder()
            source.javaClass.declaredFields.filterNot { it.isStatic }.forEach { field ->
                field.isAccessible = true
                applyInternal(sb, field.name, field.get(source), predicate, consumer)
            }
        }

        fun apply(
            source: JsonNode,
            predicate: (String) -> Boolean = { true },
            consumer: (String) -> Unit
        ) {
            val sb = StringBuilder()
            source.fields().forEach { (key, value) ->
                applyInternal(sb, key, value, predicate, consumer)
            }
        }

        private fun applyInternal(
            sb: StringBuilder,
            key: String,
            value: Any?,
            predicate: (String) -> Boolean,
            consumer: (String) -> Unit
        ) {
            if (value == null || !predicate(key)) {
                return
            }

            writeVar(sb, key)
            sb.append(": ")
            writeVar(sb, value)

            consumer(sb.toString())
            sb.clear()
        }

        fun writeVar(sb: StringBuilder, value: Any?) {
            when (value) {
                is String -> sb.append('"')
                    .append(value
                        .replace("\\", "\\\\")
                        .replace("\n", "\\n")
                        .replace("\"", "\\\""))
                    .append('"')

                is Boolean -> sb.append(value)
                is Number -> sb.append(value.toCompactString())
                is Pair<*, *> -> writeIt(sb, value.toList().iterator())
                is JsonNode -> when (value.nodeType) {
                    JsonNodeType.ARRAY -> writeIt(sb, value.elements())
                    JsonNodeType.BOOLEAN -> writeVar(sb, value.asBoolean())
                    JsonNodeType.BINARY, JsonNodeType.NUMBER -> writeVar(sb, value.asDouble())
                    JsonNodeType.STRING -> writeVar(sb, value.asText())
                    JsonNodeType.OBJECT, JsonNodeType.POJO -> writeObj(sb, value)
                    JsonNodeType.MISSING, JsonNodeType.NULL, null -> writeVar(sb, null)
                }

                is Iterable<*> -> writeIt(sb, value.iterator())

                is JsonParseable -> writeVar(sb, value.jsonValue)
                null -> sb.append("null")
                else -> throw IllegalArgumentException("unsupported type (class=${value.javaClass}, value=${value})")
            }
        }


        private fun writeObj(sb: StringBuilder, node: JsonNode) {
            val fields = node.fields()
            sb.append("{ ")
            if (fields.hasNext()) {
                val (key, value) = fields.next()
                sb.append('"').append(key).append("\": ")
                writeVar(sb, value)
            }
            while (fields.hasNext()) {
                val (key, value) = fields.next()
                writeVar(sb, key, value)
            }
            sb.append(" }")
        }

        private fun writeVar(sb: StringBuilder, name: String?, value: Any?) {
            if (value == null) return
            sb.append(", \"").append(name).append("\": ")
            writeVar(sb, value)
        }

        private fun writeIt(sb: StringBuilder, it: Iterator<*>) {
            sb.append('[')

            if (it.hasNext()) writeVar(sb, it.next())
            while (it.hasNext()) {
                sb.append(", ")
                writeVar(sb, it.next())
            }
            sb.append(']')
        }
    }
}

fun Number.toCompactString(): String {
    val formatNumber = String.format("%.6f", toDouble())
    var subStringTo = formatNumber.length

    // find not 0 index
    while (subStringTo >= 1) {
        val c = formatNumber[subStringTo - 1]
        if (c != '0') {
            break
        }
        subStringTo--
    }

    // remove . if there's no decimal point
    if (formatNumber[subStringTo - 1] == '.') {
        subStringTo--
    }
    return formatNumber.substring(0, subStringTo)
}
