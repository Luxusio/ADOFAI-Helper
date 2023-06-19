package io.luxus.adofai.lib.util

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class NumberUtilsKtTest : BehaviorSpec({
    Given("a number") {
        val number = 10
        When("requireRange is called") {
            Then("it should throw an exception if the number is smaller than range") {
                shouldThrow<IllegalArgumentException> {
                    number.requireRange(11, 20)
                }
            }
            Then("it should throw an exception if the number is bigger than range") {
                shouldThrow<IllegalArgumentException> {
                    number.requireRange(1, 9)
                }
            }
            Then("it should return the number if the number is in range") {
                number.requireRange(1, 20) shouldBe number
            }
        }
    }
})
