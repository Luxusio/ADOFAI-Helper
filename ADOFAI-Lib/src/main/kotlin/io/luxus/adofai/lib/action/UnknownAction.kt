package io.luxus.adofai.lib.action

import com.fasterxml.jackson.databind.JsonNode

@EventType(jsonValue = "", builderClass = UnknownAction.Builder::class, single = false)
class UnknownAction(
    val rawData: JsonNode
) : Action(UnknownAction::class.java, null) {

    override fun toBuilder() = Builder()
        .active(active)
        .rawData(rawData)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var rawData: JsonNode? = null
            private set

        fun rawData(rawData: JsonNode?) = apply { this.rawData = rawData }

        override fun build() = UnknownAction(rawData!!)
    }

}
