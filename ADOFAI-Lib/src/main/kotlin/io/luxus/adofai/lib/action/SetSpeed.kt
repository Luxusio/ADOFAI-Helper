package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.SpeedType

class SetSpeed private constructor(
    active: Boolean?,
    val speedType: SpeedType,
    val beatsPerMinute: Double,
    val bpmMultiplier: Double,
    val angleOffset: Double,
) : Action(SetSpeed::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .speedType(speedType)
        .beatsPerMinute(beatsPerMinute)
        .bpmMultiplier(bpmMultiplier)
        .angleOffset(angleOffset)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var speedType: SpeedType = SpeedType.BPM
            private set
        var beatsPerMinute: Double = 100.0
            private set
        var bpmMultiplier: Double = 1.0
            private set
        var angleOffset: Double = 0.0
            private set


        fun speedType(speedType: SpeedType) = apply { this.speedType = speedType }

        fun beatsPerMinute(beatsPerMinute: Double) = apply { this.beatsPerMinute = beatsPerMinute }

        fun bpmMultiplier(bpmMultiplier: Double) = apply { this.bpmMultiplier = bpmMultiplier }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }

        override fun build() = SetSpeed(
            active,
            speedType,
            beatsPerMinute,
            bpmMultiplier,
            angleOffset,
        )
    }
}
