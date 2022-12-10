package io.luxus.lib.adofai

import java.io.InputStream

object TestHelper {

    val testLevels = listOf(
        "/file/all-r90.adofai"
    )

    fun testLevelIs(): InputStream =
        javaClass.getResourceAsStream(testLevels[0])

}