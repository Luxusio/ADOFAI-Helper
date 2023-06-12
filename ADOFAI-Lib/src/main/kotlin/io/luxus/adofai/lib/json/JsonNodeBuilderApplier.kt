package io.luxus.adofai.lib.json

import com.fasterxml.jackson.databind.JsonNode
import io.luxus.adofai.lib.property.*
import io.luxus.adofai.lib.util.getParameterTypes
import io.luxus.adofai.lib.util.toMutableMap

class JsonNodeBuilderApplier(
    private val jsonDeserializerMap: Map<List<Class<*>>, JsonDeserializer<*>>
) {
    fun apply(jsonNode: JsonNode, target: Any): Map<String, JsonNode> {
        val map = jsonNode.toMutableMap()

        val nameMethodMap = target.javaClass.methods
            .filter { it.parameterCount == 1 }
            .groupBy { it.name }

        val it = map.iterator()
        while (it.hasNext()) {
            val (key, value) = it.next()
            nameMethodMap[key]?.forEach { method ->
                val types = method.getParameterTypes(0)
                val deserializer = jsonDeserializerMap[types]
                if (deserializer != null) {
                    method.invoke(target, deserializer.deserialize(value))
                    it.remove()
                }
            }
        }

        return map
    }

    companion object {
        fun create(): JsonNodeBuilderApplier {
            val deserializers = listOf(
                JsonDeserializer.create(String::class.java) { it.asText() },
                JsonDeserializer.create(Long::class.java) { it.asLong() },
                JsonDeserializer.create(Double::class.java) { it.asDouble() },
                JsonDeserializer.create(Boolean::class.java) { it.asBoolean() },
                JsonDeserializer.create(Color::class.java) { Color.Builder().rgb(it.asText()).build() },
                JsonDeserializer.create(AlphaColor::class.java) { AlphaColor.Builder().rgba(it.asText()).build() },
                JsonDeserializer.createPair(Long::class.java) { it.asLong() },
                JsonDeserializer.createPair(Double::class.java) { it.asDouble() },
                JsonDeserializer.create(AlphaColor::class.java) { AlphaColor.Builder().rgba(it.asText()).build() },
                JsonDeserializer.fromJsonParseableEnum(BGDisplayModeType::class.java),
                JsonDeserializer.fromJsonParseableEnum(CameraRelativeTo::class.java),
                JsonDeserializer.fromJsonParseableEnum(DecorationRelativeTo::class.java),
                JsonDeserializer.fromJsonParseableEnum(DefaultBGShapeType::class.java),
                JsonDeserializer.fromJsonParseableEnum(Ease::class.java),
                JsonDeserializer.fromJsonParseableEnum(Hitsound::class.java),
                JsonDeserializer.fromJsonParseableEnum(SpecialArtistType::class.java),
                JsonDeserializer.fromJsonParseableEnum(Toggle::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackAnimation::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackColorPulse::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackColorType::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackDisappearAnimation::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackStyle::class.java),
            )

            return JsonNodeBuilderApplier(deserializers.associateBy { it.targetClass })
        }
    }
}
