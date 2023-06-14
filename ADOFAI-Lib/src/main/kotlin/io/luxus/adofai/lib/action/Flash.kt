package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.AlphaColor
import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.Plane

class Flash private constructor(
    active: Boolean?,
    val duration: Double,
    val plane: Plane,
    val startColor: AlphaColor,
    val startOpacity: Double,
    val endColor: AlphaColor,
    val endOpacity: Double,
    val angleOffset: Double,
    val ease: Ease,
    val eventTag: String,
) : Action(Flash::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .plane(plane)
        .startColor(startColor)
        .startOpacity(startOpacity)
        .endColor(endColor)
        .endOpacity(endOpacity)
        .angleOffset(angleOffset)
        .ease(ease)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this

        var duration: Double = 1.0
            private set
        var plane: Plane = Plane.BACKGROUND
            private set
        var startColor: AlphaColor = AlphaColor.WHITE
            private set
        var startOpacity: Double = 100.0
            private set
        var endColor: AlphaColor = AlphaColor.WHITE
            private set
        var endOpacity: Double = 0.0
            private set
        var angleOffset: Double = 0.0
            private set
        var ease: Ease = Ease.LINEAR
            private set
        var eventTag: String = ""
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun plane(plane: Plane) = apply { this.plane = plane }
        fun startColor(startColor: AlphaColor) = apply { this.startColor = startColor }
        fun startOpacity(startOpacity: Double) = apply { this.startOpacity = startOpacity }
        fun endColor(endColor: AlphaColor) = apply { this.endColor = endColor }
        fun endOpacity(endOpacity: Double) = apply { this.endOpacity = endOpacity }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun ease(ease: Ease) = apply { this.ease = ease }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = Flash(
            active,
            duration,
            plane,
            startColor,
            startOpacity,
            endColor,
            endOpacity,
            angleOffset,
            ease,
            eventTag,
        )
    }
}
