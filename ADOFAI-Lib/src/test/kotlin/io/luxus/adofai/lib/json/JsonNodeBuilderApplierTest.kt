package io.luxus.adofai.lib.json

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.luxus.adofai.lib.CustomLevelSetting
import io.luxus.adofai.lib.util.forAllAdofaiFiles

class JsonNodeBuilderApplierTest : BehaviorSpec({
    forAllAdofaiFiles { file ->
        Given("Adofai file and JsonNodeBuilderApplier and CustomLevelSettingBuilder") {
            val jsonNode = ObjectMapper().readTree(AdofaiJsonInputStream(file.inputStream()))["settings"]
            val customLevelSettingBuilder = CustomLevelSetting.Builder()
            val applier = JsonNodeBuilderApplier.create()
            When("apply is called") {
                applier.apply(jsonNode, customLevelSettingBuilder)
                Then("it should not throw any exception") {
                    // no exception
                }
            }
        }
    }
})
