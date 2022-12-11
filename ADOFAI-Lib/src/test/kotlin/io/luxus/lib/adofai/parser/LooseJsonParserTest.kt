package io.luxus.lib.adofai.parser

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldNotBe
import io.luxus.lib.adofai.parser.json.JsonToken
import io.luxus.lib.adofai.parser.json.LooseJsonParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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

    @Test
    fun `nextToken with value should return valid value`() {
        // given
        val parser = LooseJsonParser(invalidJsonString.byteInputStream())

        // when
        val values = mutableListOf<String>()
        while (true) {
            parser.nextToken() ?: break
            values.add(parser.value())
        }

        // then
        values shouldContainExactly listOf(
            "{",
            "key",
            "[",
            "{",
            "name",
            "a",
            "age",
            "1",
            "}",
            "{",
            "}",
            "]",
            "a",
            "2",
            "}"
        )
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "{]", "[}"
    ])
    fun `read with invalid closing character should throw exception`(value: String) {
        // given
        // value
        val parser = LooseJsonParser(value.byteInputStream())

        // when & then
        assertThrows<IllegalArgumentException> {
            while (true) {
                parser.nextToken() ?: break
            }
        }
    }

}