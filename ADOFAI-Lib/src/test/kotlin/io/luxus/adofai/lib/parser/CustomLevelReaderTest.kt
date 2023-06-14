package io.luxus.adofai.lib.parser

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.luxus.adofai.lib.action.UnknownAction
import io.luxus.adofai.lib.json.AdofaiJsonInputStream
import io.luxus.adofai.lib.util.forAllAdofaiFiles

class CustomLevelReaderTest : BehaviorSpec({
    forAllAdofaiFiles { file ->
        Given("Adofai json") {
            val jsonNode = ObjectMapper().readTree(AdofaiJsonInputStream(file.inputStream()))

            When("read action with CustomLevelReader") {
                val actionNodes = jsonNode["actions"]
                val results = actionNodes.map { CustomLevelReader.INSTANCE.readAction(it) }

                then("no exception, no unknown action") {
                    results.mapNotNull { it.second?.message }
                        .joinToString("\n") shouldBe ""
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
        }
    }
})
