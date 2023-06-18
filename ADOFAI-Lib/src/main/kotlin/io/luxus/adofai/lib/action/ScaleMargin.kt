package io.luxus.adofai.lib.action

@EventType(jsonValue = "ScaleMargin", builderClass = ScaleMargin.Builder::class, single = false)
class ScaleMargin private constructor(
    active: Boolean?,
    val scale: Long,
) : Action(ScaleMargin::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .scale(scale)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var scale: Long = 100L
            private set

        fun scale(scale: Long) = apply { this.scale = scale }

        override fun build() = ScaleMargin(
            active,
            scale,
        )
    }
}
