package io.luxus.adofai.lib.action

@EventType(jsonValue = "ScreenTile", builderClass = ScreenTile.Builder::class, single = false)
data class ScreenTile(
    override val active: Boolean?,
    val tile: Pair<Double, Double>,
    val angleOffset: Double,
    val eventTag: String,
) : Action(ScreenTile::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .tile(tile)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var tile: Pair<Double, Double> = Pair(1.0, 1.0)
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun tile(tile: Pair<Double, Double>) = apply { this.tile = tile }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = ScreenTile(
            active,
            tile,
            angleOffset,
            eventTag,
        )
    }
}
