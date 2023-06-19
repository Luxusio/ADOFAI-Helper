package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "HallOfMirrors", builderClass = HallOfMirrors.Builder::class, single = false)
data class HallOfMirrors(
    override val active: Boolean?,
    val enabled: Toggle,
    val angleOffset: Double,
    val eventTag: String,
) : Action(HallOfMirrors::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .enabled(enabled)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var enabled: Toggle = Toggle.ENABLED
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun enabled(enabled: Toggle) = apply { this.enabled = enabled }

        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }

        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = HallOfMirrors(
            active,
            enabled,
            angleOffset,
            eventTag,
        )
    }
}
