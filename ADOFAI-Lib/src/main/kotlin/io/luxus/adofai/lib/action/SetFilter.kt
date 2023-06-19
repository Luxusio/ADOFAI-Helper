package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.Filter
import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "SetFilter", builderClass = SetFilter.Builder::class, single = false)
data class SetFilter(
    override val active: Boolean?,
    val filter: Filter,
    val enabled: Toggle,
    val intensity: Long,
    val duration: Double,
    val ease: Ease,
    val disableOthers: Toggle,
    val angleOffset: Double,
    val eventTag: String,
) : Action(SetFilter::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .filter(filter)
        .enabled(enabled)
        .intensity(intensity)
        .duration(duration)
        .ease(ease)
        .disableOthers(disableOthers)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var filter: Filter = Filter.GRAYSCALE
            private set
        var enabled: Toggle = Toggle.ENABLED
            private set
        var intensity: Long = 100L
            private set
        var duration: Double = 0.0
            private set
        var ease: Ease = Ease.LINEAR
            private set
        var disableOthers: Toggle = Toggle.DISABLED
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun filter(filter: Filter) = apply { this.filter = filter }
        fun enabled(enabled: Toggle) = apply { this.enabled = enabled }
        fun intensity(intensity: Long) = apply { this.intensity = intensity }
        fun duration(duration: Double) = apply { this.duration = duration }
        fun ease(ease: Ease) = apply { this.ease = ease }
        fun disableOthers(disableOthers: Toggle) = apply { this.disableOthers = disableOthers }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = SetFilter(
            active,
            filter,
            enabled,
            intensity,
            duration,
            ease,
            disableOthers,
            angleOffset,
            eventTag,
        )
    }
}
