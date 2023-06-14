package io.luxus.adofai.lib.parser

import com.fasterxml.jackson.databind.JsonNode
import io.luxus.adofai.lib.action.*
import io.luxus.adofai.lib.json.JsonNodeBuilderApplier
import io.luxus.adofai.lib.util.toMutableMap

class CustomLevelReader(
    val actionBuilderCreatorMap: Map<String, () -> Action.Builder<*>>,
    val jsonNodeBuilderApplier: JsonNodeBuilderApplier,
) {
    fun readAction(jsonNode: JsonNode): Pair<Action, Exception?> {
        try {
            val jsonValueMap = jsonNode.toMutableMap()

            val eventType = jsonValueMap.remove("eventType") ?: throw IllegalArgumentException("eventType not found")
            val builderCreator = actionBuilderCreatorMap[eventType.asText()]
                ?: throw IllegalArgumentException("unknown eventType")

            val builder = builderCreator()
            val result = jsonNodeBuilderApplier.apply(jsonNode, builder).apply {
                remove("eventType")
                remove("floor")
            }

            if (result.isNotEmpty()) {
                throw IllegalArgumentException("unknown properties: ${result.keys}")
            }

            return Pair(builder.build(), null)
        } catch (e: Exception) {
            return Pair(UnknownAction(jsonNode), e)
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
