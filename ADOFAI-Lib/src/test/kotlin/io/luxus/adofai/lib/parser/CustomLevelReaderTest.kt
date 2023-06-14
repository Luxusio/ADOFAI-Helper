package io.luxus.adofai.lib.parser

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldNotContain
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
                    results.filter { it.second != null }.shouldBeEmpty()
                    results.map { it.first.javaClass } shouldNotContain UnknownAction::class.java
                }
            }
        }
    }
})
