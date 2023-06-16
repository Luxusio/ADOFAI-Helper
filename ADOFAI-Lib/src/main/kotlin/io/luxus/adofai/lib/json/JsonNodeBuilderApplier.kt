package io.luxus.adofai.lib.json

import com.fasterxml.jackson.databind.JsonNode
import io.luxus.adofai.lib.property.*
import io.luxus.adofai.lib.util.getParameterTypes
import io.luxus.adofai.lib.util.toMutableMap
import java.lang.reflect.Method
import java.util.concurrent.ConcurrentHashMap

class JsonNodeBuilderApplier(
    jsonDeserializers: List<JsonDeserializer<*>>,
) {
    private val jsonDeserializerMap: Map<List<Class<*>>, JsonDeserializer<*>> =
        jsonDeserializers.associateBy { it.targetClass }

    private val targetClassDeserializerMap: MutableMap<Class<*>, Map<String, Pair<Method, JsonDeserializer<*>>>> = ConcurrentHashMap()

    fun apply(jsonNode: JsonNode, target: Any) = apply(jsonNode.toMutableMap(), target)

    /**
     * @return Pair of failed to read nodes, and exceptions.
     */
    fun apply(jsonNodeMap: MutableMap<String, JsonNode>, target: Any): Pair<MutableMap<String, JsonNode>, List<Exception>> {
        val deserializerMap = getDeserializerMap(target.javaClass)
        val exceptions = mutableListOf<Exception>()

        val it = jsonNodeMap.iterator()
        while (it.hasNext()) {
            val (key, value) = it.next()
            try {
                deserializerMap[key]?.let { (method, deserializer) ->
                    method.invoke(target, deserializer.deserialize(value))
                    it.remove()
                }
            } catch (e: Exception) {
                exceptions.add(e)
            }
        }

        return Pair(jsonNodeMap, exceptions)
    }

    private fun getDeserializerMap(clazz: Class<*>): Map<String, Pair<Method, JsonDeserializer<*>>> {
        return targetClassDeserializerMap.computeIfAbsent(clazz) { targetClazz ->
            targetClazz.methods
                .filter { it.parameterCount == 1 }
                .groupBy { it.name }
                .mapNotNull { (name, methods) ->
                    methods.firstNotNullOfOrNull { method ->
                        val types = method.getParameterTypes(0)
                        jsonDeserializerMap[types]?.let { Pair(method, it) }
                    }?.let { name to it }
                }
                .toMap()
        }
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
                JsonDeserializer.create(List::class.java, String::class.java) { jsonNode -> jsonNode.map { it.asText() } },
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
