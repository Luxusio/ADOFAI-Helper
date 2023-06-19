package io.luxus.adofai.lib.action

@EventType(jsonValue = "FreeRoamRemove", builderClass = FreeRoamRemove.Builder::class, single = false)
data class FreeRoamRemove(
    override val active: Boolean?,
    val position: Pair<Double, Double>,
    val size: Pair<Double, Double>,
) : Action(FreeRoamRemove::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .position(position)
        .size(size)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var position: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var size: Pair<Double, Double> = Pair(0.0, 0.0)
            private set

        fun position(position: Pair<Double, Double>) = apply { this.position = position }
        fun size(size: Pair<Double, Double>) = apply { this.size = size }

        override fun build() = FreeRoamRemove(
            active,
            position,
            size,
        )
    }
}
