package io.luxus.adofai.lib.parser

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.matchers.types.shouldBeInstanceOf
import io.luxus.adofai.lib.action.*
import io.luxus.adofai.lib.json.AdofaiJsonInputStream
import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.Planets
import io.luxus.adofai.lib.property.TilePosition
import io.luxus.adofai.lib.property.Toggle
import io.luxus.adofai.lib.util.forAllAdofaiFiles

class CustomLevelReaderTest : BehaviorSpec({

    val objectMapper = ObjectMapper()

    forAllAdofaiFiles { file ->
        Given("Adofai json") {
            val jsonNode = objectMapper.readTree(AdofaiJsonInputStream(file.inputStream()))

            When("read settings") {
                val settingsNode = jsonNode["settings"]
                val (levelSettings, exceptions) = CustomLevelReader.INSTANCE.readCustomLevelSetting(settingsNode)

                Then("no exception return") {
                    exceptions.joinToString("\n").shouldBeEmpty()
                }
                Then("unknownProperties should be empty") {
                    levelSettings.unknownProperties.shouldBeEmpty()
                }
            }

            When("read actions") {
                val actionNodes = jsonNode["actions"]
                val results = actionNodes.map { CustomLevelReader.INSTANCE.readAction(it) }

                Then("no exception return") {
                    results.flatMap { it.second }
                        .joinToString("\n") shouldBe ""
                }

                Then("no UnknownAction return") {
                    results.mapNotNull {
                        val first = it.first
                        if (first is UnknownAction) {
                            first.rawData.toPrettyString()
                        } else {
                            null
                        }
                    }.joinToString("\n") shouldBe ""
                }
            }

            When("read decorations") {
                val actionNodes = jsonNode["decorations"]
                val results = actionNodes?.map { CustomLevelReader.INSTANCE.readAction(it) } ?: listOf()

                Then("no exception return") {
                    results.flatMap { it.second }
                        .joinToString("\n") shouldBe ""
                }

                Then("no UnknownAction return") {
                    results.mapNotNull {
                        val first = it.first
                        if (first is UnknownAction) {
                            first.rawData.toPrettyString()
                        } else {
                            null
                        }
                    }.joinToString("\n") shouldBe ""
                }
            }

            When("read custom level") {
                val (customLevel, exceptions) = CustomLevelReader.INSTANCE.read(jsonNode)

                Then("no exception return") {
                    exceptions.joinToString("\n") shouldBe ""
                }

                Then("tiles should not be empty") {
                    customLevel.tiles.shouldNotBeEmpty()
                }

                Then("no UnknownAction return") {
                    customLevel.tiles.flatMap { it.actionMap.values }.mapNotNull {
                        if (it is UnknownAction) {
                            it.rawData.toPrettyString()
                        } else {
                            null
                        }
                    }.joinToString("\n") shouldBe ""
                }

                Then("there's no Decoration in tile actionMap") {
                    customLevel.tiles.flatMap { it.actionMap.values }
                        .filterIsInstance<Decoration>().shouldBeEmpty()
                }
            }
        }
    }

    Given("floor != null Decoration jsonNode") {
        val jsonString =
            """{ "floor": 195, "eventType": "AddDecoration", "decorationImage": "", "position": [0, 0], "relativeTo": "Tile", "pivotOffset": [0, 0], "rotation": 0, "lockRotation": "Disabled", "scale": [100, 100], "lockScale": "Disabled", "tile": [1, 1], "color": "ffffff", "opacity": 100, "depth": -1, "parallax": [0, 0], "parallaxOffset": [0, 0], "tag": "", "imageSmoothing": "Enabled", "blendMode": "None", "maskingType": "None", "useMaskingDepth": "Disabled", "maskingFrontDepth": -1, "maskingBackDepth": -1, "failHitbox": "Disabled", "failHitboxType": "Box", "failHitboxScale": [100, 100], "failHitboxOffset": [0, 0], "failHitboxRotation": 0  }"""
        val jsonNode = objectMapper.readTree(jsonString)
        When("read Action") {
            val (action, exceptions) = CustomLevelReader.INSTANCE.readAction(jsonNode)

            Then("instance should be AddDecoration") {
                action.shouldBeInstanceOf<AddDecoration>()
            }

            Then("floor should be 195") {
                action as AddDecoration
                action.floor shouldBe 195L
            }

        }
    }

    table(
        headers("jsonString", "action"),
        // @formatter:off
        row(
            """{ "floor": 187, "eventType": "MultiPlanet", "planets": "ThreePlanets" }""",
            MultiPlanet(null, Planets.THREE_PLANETS)
        ),
        row(
            """{ "floor": 187, "eventType": "MultiPlanet", "planets": 3 }""",
            MultiPlanet(null, Planets.THREE_PLANETS)
        ),
        row(
            """{ "floor": 98, "eventType": "MoveTrack", "startTile": [0, "ThisTile"], "endTile": [0, "ThisTile"], "gapLength": 0, "duration": 1, "positionOffset": [1.1, null], "rotationOffset": 1.1, "opacity": 10.1, "angleOffset": 1.1, "ease": "Linear", "eventTag": "asdf" }""",
            MoveTrack(null, Pair(0, TilePosition.THIS_TILE), Pair(0, TilePosition.THIS_TILE), 0, 1.0, Pair(1.1, null), 1.1, null, 10.1, 1.1, Ease.LINEAR, "asdf")
        ),
        row(
            """{ "floor": 98, "eventType": "MoveTrack", "startTile": [0, "ThisTile"], "endTile": [0, "ThisTile"], "gapLength": 0, "duration": 1, "positionOffset": [null, null], "rotationOffset": 1.1, "opacity": 10.1, "angleOffset": 1.1, "ease": "Linear", "eventTag": "asdf" }""",
            MoveTrack(null, Pair(0, TilePosition.THIS_TILE), Pair(0, TilePosition.THIS_TILE), 0, 1.0, Pair(null, null), 1.1, null, 10.1, 1.1, Ease.LINEAR, "asdf")
        ),
        row(
            """{ "floor": 101, "eventType": "PositionTrack", "positionOffset": [-10.01, -1.01], "relativeTo": [0, "ThisTile"], "rotation": 0.01, "scale": 100.01, "opacity": 99.99, "justThisTile": "Disabled", "editorOnly": "Enabled", "stickToFloors": "Disabled" }""",
            PositionTrack(null, Pair(-10.01, -1.01), Pair(0, TilePosition.THIS_TILE), 0.01, 100.01, 99.99, Toggle.DISABLED, Toggle.ENABLED, Toggle.DISABLED)
        )
        // @formatter:on
    ).forAll { jsonString, action ->
        Given("jsonString") {
            val jsonNode = objectMapper.readTree(jsonString)
            When("read Action") {
                val (result, exceptions) = CustomLevelReader.INSTANCE.readAction(jsonNode)

                Then("action should be equal to expected") {
                    result shouldBe action
                }
            }
        }
    }
})
