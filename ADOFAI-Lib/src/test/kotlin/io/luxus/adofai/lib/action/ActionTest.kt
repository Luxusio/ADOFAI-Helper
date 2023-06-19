package io.luxus.adofai.lib.action

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.luxus.adofai.lib.CustomLevel
import io.luxus.adofai.lib.util.resourceFile

class ActionTest : BehaviorSpec({
    Given("default action builders") {
        val actionBuilders = Action.CLASSES
            .filterKeys { it !in Action.LEGACY_ACTIONS }
            .map { it.value }
            .map {
                val constructor = it.builderClass.java
                    .constructors
                    .first { constructor -> constructor.parameters.isEmpty() }
                it.jsonValue to constructor.newInstance() as Action.Builder<*>
            }

        When("build") {
            val results = actionBuilders.map { (name, builder) -> Pair(name, builder.build()) }

            Then("result should be equal to default action") {
                val file = resourceFile("adofai/default/default-actions.adofai")
                val customLevel = CustomLevel.read(file.inputStream())
                val defaultActionMap = (customLevel.decorations + customLevel.tiles.flatMap { it.actionMap.values })
                    .associateBy { it.javaClass.getAnnotation(EventType::class.java).jsonValue }

                results.forEach { (name, action) ->
                    action shouldBe defaultActionMap[name]
                }
            }
        }
    }
})
