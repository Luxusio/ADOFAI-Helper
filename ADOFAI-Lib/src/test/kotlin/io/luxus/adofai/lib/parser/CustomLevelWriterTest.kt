package io.luxus.adofai.lib.parser

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.luxus.adofai.lib.CustomLevel
import io.luxus.adofai.lib.json.AdofaiJsonInputStream
import io.luxus.adofai.lib.util.forAllAdofaiFiles
import io.luxus.adofai.lib.util.recentAdofaiFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class CustomLevelWriterTest : BehaviorSpec({
    val objectMapper = ObjectMapper()

    forAllAdofaiFiles { file ->
        Given("customLevel") {
            val customLevel = CustomLevel.read(file.inputStream())
            When("write to outputStream") {
                val outputStream = ByteArrayOutputStream()
                customLevel.write(outputStream)

                Then("it can serialized by ObjectMapper") {
                    objectMapper.readTree(AdofaiJsonInputStream(ByteArrayInputStream(outputStream.toByteArray())))
                }

                Then("read result should be equal to original customLevel") {
                    val result = CustomLevel.read(ByteArrayInputStream(outputStream.toByteArray()))
                    result shouldBe customLevel
                }
            }
        }
    }

    Given("recentAdofaiFile customLevel") {
        val file = recentAdofaiFile()
        val customLevel = CustomLevel.read(file.inputStream())
        When("write to outputStream") {
            val outputStream = ByteArrayOutputStream()
            customLevel.write(outputStream)

            Then("result jsonString should be equal to original jsonString") {
                val result = AdofaiJsonInputStream(ByteArrayInputStream(outputStream.toByteArray()))
                    .readAllBytes().toString(Charsets.UTF_8)
                val original = AdofaiJsonInputStream(file.inputStream())
                    .readAllBytes().toString(Charsets.UTF_8)

                result shouldBe original
            }
        }
    }
})
