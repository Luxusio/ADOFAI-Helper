package io.luxus.adofai.lib.util

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.io.InputStreamReader

class ReaderExtensionsKtTest : BehaviorSpec({
    Given("utf8 file") {
        val file = adofaiFiles()[0]
        When("read all char with readChar") {

            val sb = StringBuilder()
            InputStreamReader(file.inputStream(), Charsets.UTF_8)
                .use { reader ->
                    while (true) {
                        val character = reader.readChar() ?: break
                        sb.append(character)
                    }
                }

            Then("read result should be same with readText") {
                sb.toString() shouldBe file.readText()
                println(sb.toString())
            }
        }
    }
})
