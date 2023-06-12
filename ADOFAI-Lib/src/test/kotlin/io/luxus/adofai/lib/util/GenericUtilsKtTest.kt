package io.luxus.adofai.lib.util

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.luxus.adofai.lib.CustomLevelSetting

class GenericUtilsKtTest : BehaviorSpec({
    Given("single type method") {
        val target = CustomLevelSetting.Builder::class.java.getMethod("version", Long::class.java)
        When("execute getParameterTypes") {
            val result = target.getParameterTypes(0)
            Then("result should be list of Long class") {
                result shouldBe listOf(Long::class.java)
            }
        }
    }

    Given("multiple type method") {
        val target = CustomLevelSetting.Builder::class.java.getMethod("parallax", Pair::class.java)
        When("execute getParameterTypes") {
            val result = target.getParameterTypes(0)
            Then("result should be list of Pair, Double, Double class") {
                result shouldBe listOf(Pair::class.java, java.lang.Double::class.java, java.lang.Double::class.java)
            }
        }
    }
})
