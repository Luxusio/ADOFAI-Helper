package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.HoldMidSound
import io.luxus.adofai.lib.property.HoldMidSoundTimingRelativeTo
import io.luxus.adofai.lib.property.HoldMidSoundType
import io.luxus.adofai.lib.property.HoldSoundType

@EventType(jsonValue = "SetHoldSound", builderClass = SetHoldSound.Builder::class, single = true)
data class SetHoldSound(
    override val active: Boolean?,
    val holdStartSound: HoldSoundType,
    val holdLoopSound: HoldSoundType,
    val holdEndSound: HoldSoundType,
    val holdMidSound: HoldMidSound,
    val holdMidSoundType: HoldMidSoundType,
    val holdMidSoundDelay: Double,
    val holdMidSoundTimingRelativeTo: HoldMidSoundTimingRelativeTo,
    val holdSoundVolume: Long,
) : Action(SetHoldSound::class.java, active) {

    override fun toBuilder() = Builder()
        .holdStartSound(holdStartSound)
        .holdLoopSound(holdLoopSound)
        .holdEndSound(holdEndSound)
        .holdMidSound(holdMidSound)
        .holdMidSoundType(holdMidSoundType)
        .holdMidSoundDelay(holdMidSoundDelay)
        .holdMidSoundTimingRelativeTo(holdMidSoundTimingRelativeTo)
        .holdSoundVolume(holdSoundVolume)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var holdStartSound: HoldSoundType = HoldSoundType.FUSE
            private set
        var holdLoopSound: HoldSoundType = HoldSoundType.FUSE
            private set
        var holdEndSound: HoldSoundType = HoldSoundType.FUSE
            private set
        var holdMidSound: HoldMidSound = HoldMidSound.FUSE
            private set
        var holdMidSoundType: HoldMidSoundType = HoldMidSoundType.ONCE
            private set
        var holdMidSoundDelay: Double = 0.5
            private set
        var holdMidSoundTimingRelativeTo: HoldMidSoundTimingRelativeTo = HoldMidSoundTimingRelativeTo.END
            private set
        var holdSoundVolume: Long = 100L
            private set

        fun holdStartSound(holdStartSound: HoldSoundType) = apply { this.holdStartSound = holdStartSound }
        fun holdLoopSound(holdLoopSound: HoldSoundType) = apply { this.holdLoopSound = holdLoopSound }
        fun holdEndSound(holdEndSound: HoldSoundType) = apply { this.holdEndSound = holdEndSound }
        fun holdMidSound(holdMidSound: HoldMidSound) = apply { this.holdMidSound = holdMidSound }
        fun holdMidSoundType(holdMidSoundType: HoldMidSoundType) = apply { this.holdMidSoundType = holdMidSoundType }
        fun holdMidSoundDelay(holdMidSoundDelay: Double) = apply { this.holdMidSoundDelay = holdMidSoundDelay }
        fun holdMidSoundTimingRelativeTo(holdMidSoundTimingRelativeTo: HoldMidSoundTimingRelativeTo) =
            apply { this.holdMidSoundTimingRelativeTo = holdMidSoundTimingRelativeTo }

        fun holdSoundVolume(holdSoundVolume: Long) = apply { this.holdSoundVolume = holdSoundVolume }

        override fun build() = SetHoldSound(
            active,
            holdStartSound,
            holdLoopSound,
            holdEndSound,
            holdMidSound,
            holdMidSoundType,
            holdMidSoundDelay,
            holdMidSoundTimingRelativeTo,
            holdSoundVolume,
        )
    }
}
