package io.luxus.adofai.lib.util

import com.fasterxml.jackson.databind.JsonNode

fun JsonNode.toMutableMap(): MutableMap<String, JsonNode> {
    val map = mutableMapOf<String, JsonNode>()
    this.fields().forEach { (key, value) ->
        map[key] = value
    }
    return map
}
