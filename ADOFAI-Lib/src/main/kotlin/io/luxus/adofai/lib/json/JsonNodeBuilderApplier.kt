package io.luxus.adofai.lib.json

import com.fasterxml.jackson.databind.JsonNode
import io.luxus.adofai.lib.property.*
import io.luxus.adofai.lib.util.getParameterTypes
import io.luxus.adofai.lib.util.toMutableMap

class JsonNodeBuilderApplier(
    jsonDeserializers: List<JsonDeserializer<*>>,
) {
    private val jsonDeserializerMap: Map<List<Class<*>>, JsonDeserializer<*>> =
        jsonDeserializers.associateBy { it.targetClass }

    fun apply(jsonNode: JsonNode, target: Any) = apply(jsonNode.toMutableMap(), target)

    fun apply(jsonNodeMap: MutableMap<String, JsonNode>, target: Any): MutableMap<String, JsonNode> {
        val nameMethodMap = target.javaClass.methods
            .filter { it.parameterCount == 1 }
            .groupBy { it.name }

        val it = jsonNodeMap.iterator()
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

        return jsonNodeMap
    }

    companion object {
        fun create(): JsonNodeBuilderApplier {
            val tilePositionValueMap = TilePosition.values().associateBy { it.jsonValue }

            val deserializers = listOf(
                JsonDeserializer.create(Long::class.javaObjectType) { it.asLong() },
                JsonDeserializer.create(Double::class.javaObjectType) { it.asDouble() },
                JsonDeserializer.create(Boolean::class.javaObjectType) { it.asBoolean() },
                JsonDeserializer.create(String::class.java) { it.asText() },
                JsonDeserializer.create(Long::class.java) { it.asLong() },
                JsonDeserializer.create(Double::class.java) { it.asDouble() },
                JsonDeserializer.create(Boolean::class.java) { it.asBoolean() },
                JsonDeserializer.create(Color::class.java) { Color.Builder().rgb(it.asText()).build() },
                JsonDeserializer.create(AlphaColor::class.java) { AlphaColor.Builder().rgba(it.asText()).build() },
                JsonDeserializer.createPair(Long::class.javaObjectType) { it.asLong() },
                JsonDeserializer.createPair(Double::class.javaObjectType) { it.asDouble() },
                JsonDeserializer.create(
                    Pair::class.java,
                    java.lang.Long::class.java,
                    TilePosition::class.java
                ) { jsonNode ->
                    val list = jsonNode.toList()
                    Pair(list[0].asLong(), tilePositionValueMap[list[1].asText()])
                } as JsonDeserializer<*>,
                JsonDeserializer.fromJsonParseableEnum(BGDisplayModeType::class.java),
                JsonDeserializer.fromJsonParseableEnum(BlendMode::class.java),
                JsonDeserializer.fromJsonParseableEnum(CameraRelativeTo::class.java),
                JsonDeserializer.fromJsonParseableEnum(DecorationRelativeTo::class.java),
                JsonDeserializer.fromJsonParseableEnum(DefaultBGShapeType::class.java),
                JsonDeserializer.fromJsonParseableEnum(Ease::class.java),
                JsonDeserializer.fromJsonParseableEnum(EasePartBehavior::class.java),
                JsonDeserializer.fromJsonParseableEnum(FailHitboxType::class.java),
                JsonDeserializer.fromJsonParseableEnum(Filter::class.java),
                JsonDeserializer.fromJsonParseableEnum(Font::class.java),
                JsonDeserializer.fromJsonParseableEnum(GameSound::class.java),
                JsonDeserializer.fromJsonParseableEnum(Hitsound::class.java),
                JsonDeserializer.fromJsonParseableEnum(HoldMidSound::class.java),
                JsonDeserializer.fromJsonParseableEnum(HoldMidSoundTimingRelativeTo::class.java),
                JsonDeserializer.fromJsonParseableEnum(HoldMidSoundType::class.java),
                JsonDeserializer.fromJsonParseableEnum(HoldSoundType::class.java),
                JsonDeserializer.fromJsonParseableEnum(MaskingType::class.java),
                JsonDeserializer.fromJsonParseableEnum(Plane::class.java),
                JsonDeserializer.fromJsonParseableEnum(Planets::class.java),
                JsonDeserializer.fromJsonParseableEnum(SpecialArtistType::class.java),
                JsonDeserializer.fromJsonParseableEnum(SpeedType::class.java),
                JsonDeserializer.fromJsonParseableEnum(TilePosition::class.java),
                JsonDeserializer.fromJsonParseableEnum(TargetPlanet::class.java),
                JsonDeserializer.fromJsonParseableEnum(Toggle::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackAnimation::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackColorPulse::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackColorType::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackDisappearAnimation::class.java),
                JsonDeserializer.fromJsonParseableEnum(TrackStyle::class.java),
            )

            return JsonNodeBuilderApplier(deserializers)
        }
    }
}
