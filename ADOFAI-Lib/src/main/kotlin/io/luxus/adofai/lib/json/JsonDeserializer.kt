package io.luxus.adofai.lib.json

import com.fasterxml.jackson.databind.JsonNode

abstract class JsonDeserializer<T>(
    clazz: Class<T>,
    vararg targetClass: Class<*>
) {
    val targetClass: List<Class<*>> = listOf(clazz) + targetClass.toList()
    abstract fun deserialize(jsonNode: JsonNode): T

    companion object {
        fun <T> create(
            clazz: Class<T>,
            vararg targetClass: Class<*> = arrayOf(),
            deserializer: (JsonNode) -> T
        ): JsonDeserializer<T> {
            return object : JsonDeserializer<T>(clazz, *targetClass) {
                override fun deserialize(jsonNode: JsonNode): T = deserializer(jsonNode)
            }
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> createPair(
            clazz: Class<T>,
            deserializer: (JsonNode) -> T?
        ): JsonDeserializer<Pair<T?, T?>> {
            return create(Pair::class.java, clazz, clazz) { jsonNode ->
                val list = jsonNode.toList().map { deserializer(it) }
                return@create if (list.isEmpty()) {
                    val value = deserializer(jsonNode)
                    Pair(value, value)
                } else if (list.size == 1) {
                    Pair(list[0], list[0])
                } else {
                    Pair(list[0], list[1])
                }
            } as JsonDeserializer<Pair<T?, T?>>
        }

        fun <T : JsonParseable> fromJsonParseableEnum(clazz: Class<T>): JsonDeserializer<T> {
            val valueMap = clazz.enumConstants.associateBy { it.jsonValue }
            return create(clazz) { valueMap[it.asText()] ?: error("Unknown value: ${clazz.simpleName}.${it.asText()}") }
        }
    }
}
