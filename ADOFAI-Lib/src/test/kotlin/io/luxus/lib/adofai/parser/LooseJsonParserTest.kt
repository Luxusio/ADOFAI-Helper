package io.luxus.lib.adofai.parser

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.luxus.lib.adofai.TestHelper.testLevelIs
import io.luxus.lib.adofai.parser.json.JsonToken
import io.luxus.lib.adofai.parser.json.LooseJsonParser
import org.junit.jupiter.api.Test
import java.io.BufferedInputStream

class LooseJsonParserTest {

    private val invalidJsonString =
        """
                {"key": [{"name": "a" "age": 1, , ,}, {},    , ] "a": 2}
            """.trimIndent()

    @Test
    fun `can construct item with inputStream`() {
        // given
        val inputStream = invalidJsonString.byteInputStream()

        // when
        val parser = LooseJsonParser(inputStream)

        // parser
        parser shouldNotBe null
    }

    @Test
    fun `nextToken should return valid JsonTokenType`() {
        // given
        val parser = LooseJsonParser(invalidJsonString.byteInputStream())

        // when
        val tokens = mutableListOf<JsonToken>()
        while (true) {
            val token = parser.nextToken() ?: break
            tokens.add(token)
        }

        // then
        tokens shouldContainExactly listOf(
            JsonToken.START_OBJECT,


            JsonToken.FIELD_NAME,
            JsonToken.START_ARRAY,

            JsonToken.START_OBJECT,
            JsonToken.FIELD_NAME,
            JsonToken.VALUE,
            JsonToken.FIELD_NAME,
            JsonToken.VALUE,
            JsonToken.END_OBJECT,

            JsonToken.START_OBJECT,
            JsonToken.END_OBJECT,

            JsonToken.END_ARRAY,
            JsonToken.FIELD_NAME,
            JsonToken.VALUE,


            JsonToken.END_OBJECT
        )
    }

}