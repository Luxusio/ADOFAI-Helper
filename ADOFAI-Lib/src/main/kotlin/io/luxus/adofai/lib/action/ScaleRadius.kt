package io.luxus.adofai.lib.action

class ScaleRadius private constructor(
    active: Boolean?,
    val scale: Long,
) : Action(ScaleRadius::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .scale(scale)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var scale: Long = 100L
            private set

        fun scale(scale: Long) = apply { this.scale = scale }

        override fun build() = ScaleRadius(
            active,
            scale,
        )
    }
}
