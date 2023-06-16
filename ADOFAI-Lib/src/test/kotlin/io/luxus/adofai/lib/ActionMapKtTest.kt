package io.luxus.adofai.lib

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.equals.shouldBeEqual
import io.luxus.adofai.lib.action.Action
import io.luxus.adofai.lib.action.SetSpeed
import io.luxus.adofai.lib.action.Twirl

class ActionMapKtTest : BehaviorSpec({

    table(
        headers("actions"),
        row(listOf(SetSpeed.Builder().build(), Twirl.Builder().build(), SetSpeed.Builder().build()))
    ).forAll { actions ->
        Given("actions") {
            When("create actionMap with actions") {
                val actionMap = actionMapOf(actions)

                Then("actionMap.values should be equal to actions") {
                    actionMap.values shouldBeEqual actions
                }
            }

            When("create mutableActionMap with actions") {
                val mutableActionMap = mutableActionMapOf(actions)

                Then("mutableActionMap.values should be equal to actions") {
                    mutableActionMap.values shouldBeEqual actions
                }
            }
        }
    }

    Given("multiple single type actions") {
        val actions = listOf<Action>(Twirl.Builder().build(), Twirl.Builder().build())

        When("create actionMap with actions") {
            Then("cannot create actionMap") {
                shouldThrow<IllegalArgumentException> {
                    actionMapOf(actions)
                }
            }
        }

        When("create mutableActionMap with actions") {
            Then("cannot create mutableActionMap") {
                shouldThrow<IllegalArgumentException> {
                    mutableActionMapOf(actions)
                }
            }
        }
    }


})
