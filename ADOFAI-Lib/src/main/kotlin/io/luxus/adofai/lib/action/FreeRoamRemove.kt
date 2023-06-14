package io.luxus.adofai.lib.action

class FreeRoamRemove private constructor(
    active: Boolean?,
    val size: Pair<Double, Double>,
    val position: Pair<Double, Double>,
) : Action(FreeRoamRemove::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .size(size)
        .position(position)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var size: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var position: Pair<Double, Double> = Pair(0.0, 0.0)
            private set

        fun size(size: Pair<Double, Double>) = apply { this.size = size }
        fun position(position: Pair<Double, Double>) = apply { this.position = position }

        override fun build() = FreeRoamRemove(
            active,
            size,
            position,
        )
    }
}
