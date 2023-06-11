package io.luxus.adofai.lib.json

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.luxus.adofai.lib.util.adofaiFiles
import java.io.InputStreamReader

class AdofaiJsonInputStreamTest : BehaviorSpec({
    forAll(
        table(
            headers("file"),
            adofaiFiles().map { row(it) }
        )
    ) { file ->
        Given("ADOFAI File") {
            When("read as AdofaiJsonInputStream") {
                val inputStream = AdofaiJsonInputStream(file.inputStream())
                Then("can be read by jackson") {
                    ObjectMapper().readTree(inputStream)
                        .toPrettyString() shouldNotBe ""
                }
            }
        }

        Given("Json String") {
            val json = "{\"comment\":\"코멘트 내용을 여기에 적을 수 있습니다.\\n\\n줄바꿈 및 <color=#00FF00>색상</color> 또한 지원됩니다.\"}"
            When("read as AdofaiJsonInputStream") {
                val inputStream = AdofaiJsonInputStream(json.byteInputStream())
                Then("can convert multibyte character") {
                    InputStreamReader(inputStream, Charsets.UTF_8)
                        .readText() shouldBe json
                }
            }
        }
    }
})
