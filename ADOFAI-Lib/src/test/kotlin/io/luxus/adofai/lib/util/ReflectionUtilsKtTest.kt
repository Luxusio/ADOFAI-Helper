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
import kotlin.reflect.full.memberFunctions

class ReflectionUtilsKtTest : BehaviorSpec({
    table(
        headers("method", "index", "expected"),
        row(
            CustomLevelSetting.Builder::class.memberFunctions.first { it.name == "version" }, 1,
            listOf(Long::class.java)
        ),
        row(
            CustomLevelSetting.Builder::class.memberFunctions.first { it.name == "parallax" }, 1,
            listOf(Pair::class.java, Double::class.java, Double::class.java)
        ),
        row(
            RecolorTrack.Builder::class.memberFunctions.first { it.name == "startTile" }, 1,
            listOf(Pair::class.java, Long::class.java, TilePosition::class.java)
        ),
        row(
            MoveTrack.Builder::class.memberFunctions.first { it.name == "rotationOffset" }, 1,
            listOf(java.lang.Double::class.java)
        ),
        row(
            MoveTrack.Builder::class.memberFunctions.first { it.name == "scale" }, 1,
            listOf(Pair::class.java, java.lang.Double::class.java, java.lang.Double::class.java)
        ),
        row(
            CustomLevelSetting.Builder::class.memberFunctions.first { it.name == "requiredMods" }, 1,
            listOf(List::class.java, String::class.java)
        )
    ).forAll { method, index, expected ->
        Given("method") {
            When("execute toClassList") {
                val result = method.parameters[index].type.toClassList()
                Then("result should be as expected") {
                    result shouldBe expected
                }
            }
        }
    }
})
