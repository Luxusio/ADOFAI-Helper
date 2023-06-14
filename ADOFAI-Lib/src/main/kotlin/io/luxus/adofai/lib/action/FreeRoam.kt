package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Ease

class FreeRoam private constructor(
    active: Boolean?,
    val duration: Double,
    val size: Pair<Double, Double>,
    val positionOffset: Pair<Double, Double>,
    val outTime: Long,
    val outEase: Ease,
    val countdownTicks: Long,
    val angleCorrectionDir: Long,
) : Action(FreeRoam::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .size(size)
        .positionOffset(positionOffset)
        .outTime(outTime)
        .outEase(outEase)
        .countdownTicks(countdownTicks)
        .angleCorrectionDir(angleCorrectionDir)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var duration: Double = 16.0
            private set
        var size: Pair<Double, Double> = Pair(4.0, 4.0)
            private set
        var positionOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var outTime: Long = 4L
            private set
        var outEase: Ease = Ease.IN_OUT_SINE
            private set
        var countdownTicks: Long = 4L
            private set
        var angleCorrectionDir: Long = -1L
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun size(size: Pair<Double, Double>) = apply { this.size = size }
        fun positionOffset(positionOffset: Pair<Double, Double>) = apply { this.positionOffset = positionOffset }
        fun outTime(outTime: Long) = apply { this.outTime = outTime }
        fun outEase(outEase: Ease) = apply { this.outEase = outEase }
        fun countdownTicks(countdownTicks: Long) = apply { this.countdownTicks = countdownTicks }
        fun angleCorrectionDir(angleCorrectionDir: Long) = apply { this.angleCorrectionDir = angleCorrectionDir }

        override fun build() = FreeRoam(
            active,
            duration,
            size,
            positionOffset,
            outTime,
            outEase,
            countdownTicks,
            angleCorrectionDir,
        )
    }
}
