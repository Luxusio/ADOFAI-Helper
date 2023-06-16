package io.luxus.adofai.lib.parser

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import io.luxus.adofai.lib.CustomLevel
import io.luxus.adofai.lib.CustomLevelSetting
import io.luxus.adofai.lib.Tile
import io.luxus.adofai.lib.TileAngle
import io.luxus.adofai.lib.action.*
import io.luxus.adofai.lib.json.JsonNodeBuilderApplier
import io.luxus.adofai.lib.property.LegacyTileAngle
import io.luxus.adofai.lib.property.Planets
import io.luxus.adofai.lib.util.generalizedAngle
import io.luxus.adofai.lib.util.toMutableMap

class CustomLevelReader(
    val actionBuilderCreatorMap: Map<String, () -> Action.Builder<*>>,
    val jsonNodeBuilderApplier: JsonNodeBuilderApplier,
) {

    val legacyTileAngleMap = LegacyTileAngle.values().associateBy { it.code }

    fun read(jsonNode: JsonNode): Pair<CustomLevel, List<Exception>> {
        val resultExceptions = mutableListOf<Exception>()

        val angleData = (jsonNode["angleData"]?.let { readAngleData(it) })
            ?: (jsonNode["pathData"]?.let { readPathData(it) })
            ?: throw IllegalArgumentException("angleData or pathData must be exist")
        angleData.add(0, TileAngle._0)

        val customLevelSetting = readCustomLevelSetting(jsonNode["settings"]
            ?: throw IllegalArgumentException("settings must be exist"))
            .let { (customLevelSetting, exceptions) ->
                resultExceptions += exceptions
                customLevelSetting
            }

        val tileBuilders = angleData.map { Tile.Builder().angle(it) }

        val decorations = mutableListOf<Decoration>()
        jsonNode["actions"]?.let { actionsNode ->
            actionsNode.forEach {
                val (action, exceptions) = readAction(it)
                resultExceptions += exceptions
                val floor = it["floor"].asInt()

                if (floor > 0 && floor < tileBuilders.size) {
                    if (action is Decoration) {
                        decorations.add(action)
                    } else {
                        tileBuilders[floor].actionMap.add(action)
                    }
                }
            }
        }

        jsonNode["decorations"]?.let { decorationsNode ->
            decorationsNode.forEach {
                val (action, exceptions) = readAction(it)
                resultExceptions += exceptions

                if (action is Decoration) {
                    decorations.add(action)
                }
            }
        }

        return Pair(
            CustomLevel.Builder()
                .levelSettingBuilder(customLevelSetting)
                .tileBuilders(tileBuilders)
                .decorations(decorations)
                .build(),
            resultExceptions
        )
    }

    fun readPathData(jsonNode: JsonNode): MutableList<TileAngle> {
        val pathData = jsonNode.asText().toCharArray()
            .map { legacyTileAngleMap[it] }
            .filterNotNull()

        var absoluteAngle = 0.0
        val angleData = mutableListOf<TileAngle>()

        for (angle in pathData) {
            if (angle === LegacyTileAngle.MID_SPIN) {
                angleData.add(TileAngle.MID_SPIN)
            } else {
                absoluteAngle = if (angle.relativeAngle) {
                    (absoluteAngle + 180 - angle.angle).generalizedAngle()
                } else {
                    angle.angle
                }
                angleData.add(TileAngle.create(absoluteAngle))
            }
        }

        return angleData
    }

    fun readAngleData(jsonNode: JsonNode): MutableList<TileAngle> {
        val angleData = mutableListOf<TileAngle>()

        jsonNode.forEach {
            val angleValue = it.asDouble()
            if (angleValue == 999.0) {
                angleData.add(TileAngle.MID_SPIN)
            } else {
                angleData.add(TileAngle.create(angleValue))
            }
        }
        return angleData
    }

    fun readCustomLevelSetting(jsonNode: JsonNode): Pair<CustomLevelSetting.Builder, List<Exception>> {
        val builder = CustomLevelSetting.Builder()
        val resultExceptions = mutableListOf<Exception>()

        try {
            val map = jsonNode.toMutableMap().apply {
                remove("unscaledSize")
            }

            val (rest, exceptions) = jsonNodeBuilderApplier.apply(map, builder)
            resultExceptions.addAll(exceptions)
            builder.unknownProperties(rest)
        } catch (e: Exception) {
            resultExceptions.add(e)
        }

        return Pair(builder, resultExceptions)
    }

    fun readAction(jsonNode: JsonNode): Pair<Action, List<Exception>> {
        val resultExceptions = mutableListOf<Exception>()
        try {
            val jsonValueMap = jsonNode.toMutableMap()
            val eventType = jsonValueMap.remove("eventType")?.asText()
                ?: throw IllegalArgumentException("eventType not found ($jsonValueMap)")
            val builderCreator = actionBuilderCreatorMap[eventType]
                ?: throw IllegalArgumentException("unknown eventType ($eventType)")

            val builder = builderCreator()
            if (builder !is Decoration.Builder) {
                jsonValueMap.remove("floor")
            }

            fixActionMap(eventType, jsonValueMap)
            val (rest, exceptions) = jsonNodeBuilderApplier.apply(jsonValueMap, builder)
            resultExceptions += exceptions

            if (rest.isNotEmpty()) {
                throw IllegalArgumentException("unknown properties: $eventType.${rest.keys}")
            }

            return Pair(builder.build(), resultExceptions)
        } catch (e: Exception) {
            return Pair(UnknownAction(jsonNode), resultExceptions + e)
        }
    }

    private fun fixActionMap(eventType: String, jsonValueMap: MutableMap<String, JsonNode>) {
        when (eventType) {
            "MultiPlanet" -> {
                val planets = jsonValueMap.remove("planets")
                if (planets?.isLong == true) {
                    val planetsEnum = Planets.values()
                        .firstOrNull { it.count == planets.asLong() }
                        ?: throw IllegalArgumentException("Invalid value (MultiPlanet.planets=$planets)")

                    jsonValueMap["planet"] = TextNode.valueOf(planetsEnum.jsonValue)
                }
            }
        }
    }

    companion object {
        val INSTANCE = CustomLevelReader(
            actionBuilderCreatorMap = mapOf(
                "AddDecoration" to AddDecoration::Builder,
                "AddText" to AddText::Builder,
                "AnimateTrack" to AnimateTrack::Builder,
                "AutoPlayTiles" to AutoPlayTiles::Builder,
                "Bloom" to Bloom::Builder,
                "Bookmark" to Bookmark::Builder,
                "ChangeTrack" to ChangeTrack::Builder,
                "Checkpoint" to Checkpoint::Builder,
                "ColorTrack" to ColorTrack::Builder,
                "CustomBackground" to CustomBackground::Builder,
                "EditorComment" to EditorComment::Builder,
                "Flash" to Flash::Builder,
                "FreeRoam" to FreeRoam::Builder,
                "FreeRoamRemove" to FreeRoamRemove::Builder,
                "FreeRoamTwirl" to FreeRoamTwirl::Builder,
                "HallOfMirrors" to HallOfMirrors::Builder,
                "Hide" to Hide::Builder,
                "Hold" to Hold::Builder,
                "MoveCamera" to MoveCamera::Builder,
                "MoveDecorations" to MoveDecorations::Builder,
                "MoveTrack" to MoveTrack::Builder,
                "MultiPlanet" to MultiPlanet::Builder,
                "Pause" to Pause::Builder,
                "PlaySound" to PlaySound::Builder,
                "PositionTrack" to PositionTrack::Builder,
                "RecolorTrack" to RecolorTrack::Builder,
                "RepeatEvents" to RepeatEvents::Builder,
                "ScaleMargin" to ScaleMargin::Builder,
                "ScalePlanets" to ScalePlanets::Builder,
                "ScaleRadius" to ScaleRadius::Builder,
                "ScreenScroll" to ScreenScroll::Builder,
                "ScreenTile" to ScreenTile::Builder,
                "SetConditionalEvents" to SetConditionalEvents::Builder,
                "SetFilter" to SetFilter::Builder,
                "SetHitsound" to SetHitsound::Builder,
                "SetHoldSound" to SetHoldSound::Builder,
                "SetPlanetRotation" to SetPlanetRotation::Builder,
                "SetSpeed" to SetSpeed::Builder,
                "SetText" to SetText::Builder,
                "ShakeScreen" to ShakeScreen::Builder,
                "Twirl" to Twirl::Builder,
            ),
            jsonNodeBuilderApplier = JsonNodeBuilderApplier.create(),
        )
    }

}
