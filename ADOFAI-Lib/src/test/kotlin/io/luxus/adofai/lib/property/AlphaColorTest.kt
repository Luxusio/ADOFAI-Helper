package io.luxus.adofai.lib.property

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class AlphaColorTest : BehaviorSpec({
    forAll(
        table(
            headers("color"),
            row("1eff33"),
            row("1eff33ff"),
            row("1eff3300"),
        ),
    ) { color ->
        Given("a color string") {
            When("AlphaColor is created") {
                val alphaColor = AlphaColor.Builder().rgba(color).build()
                Then("jsonValue should equal to given color") {
                    alphaColor.jsonValue shouldBe color
                }
            }
        }
    }
})
