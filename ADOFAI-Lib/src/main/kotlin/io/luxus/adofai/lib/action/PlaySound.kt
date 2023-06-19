package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Hitsound

@EventType(jsonValue = "PlaySound", builderClass = PlaySound.Builder::class, single = false)
data class PlaySound(
    override val active: Boolean?,
    val hitsound: Hitsound,
    val hitsoundVolume: Long,
    val angleOffset: Double,
    val eventTag: String,
) : Action(PlaySound::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .hitsound(hitsound)
        .hitsoundVolume(hitsoundVolume)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var hitsound: Hitsound = Hitsound.KICK
            private set
        var hitsoundVolume: Long = 100L
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun hitsound(hitsound: Hitsound) = apply { this.hitsound = hitsound }
        fun hitsoundVolume(hitsoundVolume: Long) = apply { this.hitsoundVolume = hitsoundVolume }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = PlaySound(
            active,
            hitsound,
            hitsoundVolume,
            angleOffset,
            eventTag,
        )
    }
}
