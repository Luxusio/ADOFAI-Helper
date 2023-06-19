package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.AlphaColor
import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "Bloom", builderClass = Bloom.Builder::class, single = false)
data class Bloom(
    override val active: Boolean?,
    val enabled: Toggle,
    val threshold: Double,
    val intensity: Double,
    val color: AlphaColor,
    val duration: Double,
    val ease: Ease,
    val angleOffset: Double,
    val eventTag: String,
) : Action(Bloom::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .enabled(enabled)
        .threshold(threshold)
        .intensity(intensity)
        .color(color)
        .duration(duration)
        .ease(ease)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var enabled: Toggle = Toggle.ENABLED
            private set
        var threshold: Double = 50.0
            private set
        var intensity: Double = 100.0
            private set
        var color: AlphaColor = AlphaColor.WHITE
            private set
        var duration: Double = 0.0
            private set
        var ease: Ease = Ease.LINEAR
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun enabled(enabled: Toggle) = apply { this.enabled = enabled }
        fun threshold(threshold: Double) = apply { this.threshold = threshold }
        fun intensity(intensity: Double) = apply { this.intensity = intensity }
        fun color(color: AlphaColor) = apply { this.color = color }
        fun duration(duration: Double) = apply { this.duration = duration }
        fun ease(ease: Ease) = apply { this.ease = ease }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = Bloom(
            active,
            enabled,
            threshold,
            intensity,
            color,
            duration,
            ease,
            angleOffset,
            eventTag,
        )
    }
}
