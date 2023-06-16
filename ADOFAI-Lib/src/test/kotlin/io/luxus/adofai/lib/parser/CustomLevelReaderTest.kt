package io.luxus.adofai.lib.parser

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.luxus.adofai.lib.action.Decoration
import io.luxus.adofai.lib.action.UnknownAction
import io.luxus.adofai.lib.json.AdofaiJsonInputStream
import io.luxus.adofai.lib.util.forAllAdofaiFiles

class CustomLevelReaderTest : BehaviorSpec({
    forAllAdofaiFiles { file ->
        Given("Adofai json") {
            val jsonNode = ObjectMapper().readTree(AdofaiJsonInputStream(file.inputStream()))

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
})
