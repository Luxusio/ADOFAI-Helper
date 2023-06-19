package io.luxus.adofai.lib.util

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class AdofaiUtilsKtTest : BehaviorSpec({

    table(
        headers("absoluteAngle", "relativeAngle", "planetAngle", "twirl", "expected"),
        row(0.0, 180.0, 180.0, false, 0.0),
        row(0.0, 90.0, 180.0, false, 90.0),
        row(0.0, 90.0, 180.0, true, 270.0),
        row(0.0, 45.0, 180.0, false, 135.0),
        row(0.0, 45.0, 180.0, true, 225.0),
        row(180.0, 30.0, 180.0, true, 30.0),
        row(210.0, 90.0, 180.0, true, 120.0),
        row(0.0, 30.0, 120.0, false, 90.0),
        row(0.0, 360.0, 120.0, false, 120.0),
        row(0.0, 0.0, 180.0, false, 180.0),
        row(0.0, 0.0, 180.0, true, 180.0),
        row(0.0, 0.0, 120.0, false, 240.0),
        row(0.0, 0.0, 120.0, true, 120.0),
    ).forAll { absoluteAngle, relativeAngle, planetAngle, twirl, expected ->
        Given("angle data $absoluteAngle, $relativeAngle, $planetAngle, $twirl") {
            When("calculate next absolute angle") {
                val result = calculateNextAbsoluteAngle(absoluteAngle, relativeAngle, planetAngle, twirl)
                Then("result should be $expected") {
                    result shouldBe expected
                }
            }
        }
    }

    table(
        headers("planetCount", "expected"),
        row(2L, 180.0),
        row(3L, 120.0),
    ).forAll { planetCount, expected ->
        Given("planet count") {
            When("calculate planet angle") {
                val result = planetCount.planetAngle()
                Then("result should be $expected") {
                    result shouldBe expected
                }
            }
        }
    }

    table(
        headers("rawAngle", "expected"),
        row(0.0, 0.0),
        row(150.0, 150.0),
        row(359.99, 359.99),
        row(360.0, 0.0),
        row(480.0, 120.0),
        row(720.0, 0.0),
        row(-1.0, 359.0),
        row(-360.0, 0.0),
        row(-360.45, 359.55),
    ).forAll { rawAngle, expected ->
        Given("raw angle $rawAngle") {
            When("calculate generalized angle") {
                val result = rawAngle.generalizedAngle()
                Then("result should be $expected") {
                    result shouldBe expected
                }
            }
        }
    }
})
