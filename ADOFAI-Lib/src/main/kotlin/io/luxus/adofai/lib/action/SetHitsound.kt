package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.GameSound
import io.luxus.adofai.lib.property.Hitsound

class SetHitsound private constructor(
    active: Boolean?,
    val gameSound: GameSound,
    val hitsound: Hitsound,
    val hitsoundVolume: Long,
) : Action(SetHitsound::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .gameSound(gameSound)
        .hitsound(hitsound)
        .hitsoundVolume(hitsoundVolume)

    class Builder : Action.Builder<Builder>() {
        override val self = this

        var gameSound: GameSound = GameSound.HITSOUND
            private set
        var hitsound: Hitsound = Hitsound.KICK
            private set
        var hitsoundVolume: Long = 100L
            private set

        fun gameSound(gameSound: GameSound) = apply { this.gameSound = gameSound }
        fun hitsound(hitsound: Hitsound) = apply { this.hitsound = hitsound }
        fun hitsoundVolume(hitsoundVolume: Long) = apply { this.hitsoundVolume = hitsoundVolume }

        override fun build() = SetHitsound(
            active,
            gameSound,
            hitsound,
            hitsoundVolume,
        )
    }
}
