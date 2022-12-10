package io.luxus.lib.adofai

import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import java.nio.file.Files

class CustomLevelTest {

    @Test
    fun `read should return CustomLevel`() {
        // given
        val inputStream = javaClass.getResourceAsStream("/file/all-r90.adofai")

        // when
        val customLevel = CustomLevel.read(inputStream)

        // then
        customLevel shouldNotBe null
    }

}