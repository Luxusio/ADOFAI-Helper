package io.luxus.adofai.lib

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldBeEmpty
import io.luxus.adofai.lib.util.forAllAdofaiFiles

class CustomLevelTest : BehaviorSpec({
    forAllAdofaiFiles { file ->
        Given("adofai file") {
            When("readDetailed") {
                val (_, exceptions) = CustomLevel.readDetailed(file.inputStream())
                Then("no exceptions return") {
                    exceptions.joinToString("\n") { it.message ?: "" }.shouldBeEmpty()
                }
            }
        }
    }
})
