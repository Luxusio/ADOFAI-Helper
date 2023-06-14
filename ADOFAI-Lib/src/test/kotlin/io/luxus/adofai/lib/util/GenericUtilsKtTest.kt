package io.luxus.adofai.lib.util

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.luxus.adofai.lib.CustomLevelSetting
import io.luxus.adofai.lib.action.MoveTrack
import io.luxus.adofai.lib.action.RecolorTrack
import io.luxus.adofai.lib.property.TilePosition

class GenericUtilsKtTest : BehaviorSpec({
    table(
        headers("method", "index", "expected"),
        row(CustomLevelSetting.Builder::class.java.getMethod("version", Long::class.java), 0, listOf(Long::class.java)),
        row(
            CustomLevelSetting.Builder::class.java.getMethod("parallax", Pair::class.java),
            0,
            listOf(Pair::class.java, java.lang.Double::class.java, java.lang.Double::class.java)
        ),
        row(
            RecolorTrack.Builder::class.java.getMethod("startTile", Pair::class.java), 0,
            listOf(Pair::class.java, java.lang.Long::class.java, TilePosition::class.java)
        ),
        row(
            MoveTrack.Builder::class.java.getMethod("rotationOffset", java.lang.Double::class.java),
            0,
            listOf(java.lang.Double::class.java)
        ),
        row(
            MoveTrack.Builder::class.java.getMethod("scale", Pair::class.java),
            0,
            listOf(Pair::class.java, java.lang.Double::class.java, java.lang.Double::class.java)
        )
    ).forAll { method, index, expected ->
        Given("method") {
            When("execute getParameterTypes") {
                val result = method.getParameterTypes(index)
                Then("result should be as expected") {
                    result shouldBe expected
                }
            }
        }
    }
})
