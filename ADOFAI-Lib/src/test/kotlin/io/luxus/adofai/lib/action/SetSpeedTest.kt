package io.luxus.adofai.lib.action

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.luxus.adofai.lib.action.SetSpeed.Companion.calculateBpm

class SetSpeedTest : BehaviorSpec({
    fun a(bpm: Double, angleOffset: Double): SetSpeed {
        return SetSpeed.Builder()
            .beatsPerMinute(bpm)
            .angleOffset(angleOffset)
            .build()
    }

    table(
        headers("expectedCurrTileBpm", "expectedNextTileBpm", "prevTileBpm", "travelAngle", "actions"),
        row(400.0, 400.0, 100.0, 180.0, listOf(a(400.0, 0.0))),
        row(160.0, 400.0, 100.0, 180.0, listOf(a(400.0, 90.0))),
        row(100.0, 100.0, 100.0, 180.0, listOf()),
        row(3.141592, 3.141592, 3.141592, 180.0, listOf()),
    ).forAll { expectedCurrTileBpm, expectedNextTileBpm, prevTileBpm, travelAngle, actions ->
        Given("SetSpeed actions") {
            When("calculate bpm") {
                val result = calculateBpm(prevTileBpm, travelAngle, actions)
                Then("result should be as expected") {
                    result.currTileBpm shouldBe expectedCurrTileBpm
                    result.nextTileBpm shouldBe expectedNextTileBpm
                }
            }
        }
    }
})
