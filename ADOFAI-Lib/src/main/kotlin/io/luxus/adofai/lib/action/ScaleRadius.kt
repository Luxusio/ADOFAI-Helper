package io.luxus.adofai.lib.action

@EventType(jsonValue = "ScaleRadius", builderClass = ScaleRadius.Builder::class, single = true)
data class ScaleRadius(
    override val active: Boolean?,
    val scale: Double,
) : Action(ScaleRadius::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .scale(scale)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var scale: Double = 100.0
            private set

        fun scale(scale: Double) = apply { this.scale = scale }

        override fun build() = ScaleRadius(
            active,
            scale,
        )
    }
}
