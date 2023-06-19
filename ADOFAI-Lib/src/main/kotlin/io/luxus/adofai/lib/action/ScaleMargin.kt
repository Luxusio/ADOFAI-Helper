package io.luxus.adofai.lib.action

@EventType(jsonValue = "ScaleMargin", builderClass = ScaleMargin.Builder::class, single = false)
data class ScaleMargin(
    override val active: Boolean?,
    val scale: Double,
) : Action(ScaleMargin::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .scale(scale)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var scale: Double = 100.0
            private set

        fun scale(scale: Double) = apply { this.scale = scale }

        override fun build() = ScaleMargin(
            active,
            scale,
        )
    }
}
