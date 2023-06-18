package io.luxus.adofai.lib.action

@EventType(jsonValue = "Twirl", builderClass = Twirl.Builder::class, single = true)
class Twirl private constructor(
    active: Boolean?
) : Action(Twirl::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        override fun build() = Twirl(active)
    }
}
