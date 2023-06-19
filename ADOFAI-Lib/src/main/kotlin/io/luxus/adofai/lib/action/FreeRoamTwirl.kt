package io.luxus.adofai.lib.action

@EventType(jsonValue = "FreeRoamTwirl", builderClass = FreeRoamTwirl.Builder::class, single = false)
data class FreeRoamTwirl(
    override val active: Boolean?,
    val position: Pair<Double, Double>,
) : Action(FreeRoamTwirl::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .position(position)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var position: Pair<Double, Double> = Pair(0.0, 0.0)
            private set

        fun position(position: Pair<Double, Double>) = apply { this.position = position }

        override fun build() = FreeRoamTwirl(
            active,
            position,
        )
    }
}
