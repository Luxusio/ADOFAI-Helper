package io.luxus.adofai.lib.action

@EventType(jsonValue = "ScreenScroll", builderClass = ScreenScroll.Builder::class, single = false)
class ScreenScroll private constructor(
    active: Boolean?,
    val scroll: Pair<Double, Double>,
    val angleOffset: Double,
    val eventTag: String,
) : Action(ScreenScroll::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .scroll(scroll)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var scroll: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun scroll(scroll: Pair<Double, Double>) = apply { this.scroll = scroll }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = ScreenScroll(
            active,
            scroll,
            angleOffset,
            eventTag,
        )
    }
}
