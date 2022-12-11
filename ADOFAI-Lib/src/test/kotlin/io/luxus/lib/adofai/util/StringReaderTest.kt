package io.luxus.lib.adofai.util

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import java.io.InputStream

class StringReaderTest {

    @Test
    fun `can create object with inputStream`() {
        // given
        val inputStream = "abc".byteInputStream()

        // when
        val stringReader = StringReader(inputStream)

        // then
        stringReader shouldNotBe null
    }

    @Test
    fun `readString should return full String`() {
        // given
        val string = "Hello world!"
        val stringReader = StringReader(string.byteInputStream())

        // when
        val result = stringReader.readString()

        // then
        result shouldBe string
    }

    @Test
    fun `next should return characters in valid order`() {
        // given
        val string = "Hello World●"
        val stringReader = StringReader(string.byteInputStream())

        // when & then
        stringReader.next() shouldBe 'H'
        stringReader.next() shouldBe 'e'
        stringReader.next() shouldBe 'l'
        stringReader.next() shouldBe 'l'
        stringReader.next() shouldBe 'o'
        stringReader.next() shouldBe ' '
        stringReader.next() shouldBe 'W'
        stringReader.next() shouldBe 'o'
        stringReader.next() shouldBe 'r'
        stringReader.next() shouldBe 'l'
        stringReader.next() shouldBe 'd'
        stringReader.next() shouldBe '●'
    }

    @Test
    fun `can iterate stringReader`() {
        // given
        val string = "Hello World●"
        val stringReader = StringReader(string.byteInputStream())

        // when
        val results = StringBuilder(string.length)
        while (stringReader.hasNext()) {
            results.append(stringReader.next())
        }

        // then
        results.toString() shouldBe string
    }

}