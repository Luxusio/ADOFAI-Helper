package io.luxus.lib.adofai.parser

import io.luxus.lib.adofai.TestHelper
import org.junit.jupiter.api.Test

class CustomLevelJsonReaderTest {

    @Test
    fun `create with inputStream should not throw exception`() {
        // given
        val inputStream = TestHelper.testLevelIs()

        // when & then
        CustomLevelJsonReader(inputStream)
    }


}