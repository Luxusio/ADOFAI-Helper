package io.luxus.adofai.lib

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.luxus.adofai.lib.util.forAllAdofaiFiles
import io.luxus.adofai.lib.util.resourceFile

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

    Given("default customLevel builder") {
        val customLevelBuilder = CustomLevel.Builder()
        When("build") {
            val customLevel = customLevelBuilder.build()
            Then("customLevel should be equal to default customLevel") {
                val file = resourceFile("adofai/default/default.adofai")
                val defaultCustomLevel = CustomLevel.read(file.inputStream())
                customLevel shouldBe defaultCustomLevel
            }
        }
    }
})
