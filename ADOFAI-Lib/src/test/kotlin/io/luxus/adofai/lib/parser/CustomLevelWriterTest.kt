package io.luxus.adofai.lib.parser

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.luxus.adofai.lib.CustomLevel
import io.luxus.adofai.lib.json.AdofaiJsonInputStream
import io.luxus.adofai.lib.util.forAllAdofaiFiles
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class CustomLevelWriterTest : BehaviorSpec({
    forAllAdofaiFiles { file ->
        Given("customLevel") {
            val customLevel = CustomLevel.read(file.inputStream())
            When("write to outputStream") {
                val outputStream = ByteArrayOutputStream()
                customLevel.write(outputStream)

                Then("it can serialized by ObjectMapper") {
                    ObjectMapper().readTree(AdofaiJsonInputStream(ByteArrayInputStream(outputStream.toByteArray())))
                }

                Then("read result should be equal to original customLevel") {
                    val result = CustomLevel.read(ByteArrayInputStream(outputStream.toByteArray()))
                    result shouldBe customLevel
                }
            }
        }
    }
})
