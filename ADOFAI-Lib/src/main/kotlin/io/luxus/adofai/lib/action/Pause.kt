package io.luxus.adofai.lib.action

class Pause private constructor(
    active: Boolean?,
    val duration: Double,
    val countdownTicks: Long,
    val angleCorrectionDir: Long,
) : Action(Pause::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .countdownTicks(countdownTicks)
        .angleCorrectionDir(angleCorrectionDir)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var duration: Double = 1.0
            private set
        var countdownTicks: Long = 0L
            private set
        var angleCorrectionDir: Long = -1L
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun countdownTicks(countdownTicks: Long) = apply { this.countdownTicks = countdownTicks }
        fun angleCorrectionDir(angleCorrectionDir: Long) = apply { this.angleCorrectionDir = angleCorrectionDir }

        override fun build() = Pause(
            active,
            duration,
            countdownTicks,
            angleCorrectionDir,
        )
    }
}
