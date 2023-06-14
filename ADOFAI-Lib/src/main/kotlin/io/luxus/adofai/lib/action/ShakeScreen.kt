package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Toggle

class ShakeScreen private constructor(
    active: Boolean?,
    val duration: Double,
    val strength: Double,
    val intensity: Double,
    val fadeOut: Toggle,
    val angleOffset: Double,
    val eventTag: String,
) : Action(ShakeScreen::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .strength(strength)
        .intensity(intensity)
        .fadeOut(fadeOut)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var duration: Double = 1.0
            private set
        var strength: Double = 100.0
            private set
        var intensity: Double = 100.0
            private set
        var fadeOut: Toggle = Toggle.ENABLED
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun strength(strength: Double) = apply { this.strength = strength }
        fun intensity(intensity: Double) = apply { this.intensity = intensity }
        fun fadeOut(fadeOut: Toggle) = apply { this.fadeOut = fadeOut }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = ShakeScreen(
            active,
            duration,
            strength,
            intensity,
            fadeOut,
            angleOffset,
            eventTag,
        )
    }
}
