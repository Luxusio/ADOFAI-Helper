package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Toggle

class Hold private constructor(
    active: Boolean?,
    val duration: Long,
    val distanceMultiplier: Long,
    val landingAnimation: Toggle
) : Action(Hold::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .distanceMultiplier(distanceMultiplier)
        .landingAnimation(landingAnimation)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var duration: Long = 0L
            private set
        var distanceMultiplier: Long = 100L
            private set
        var landingAnimation: Toggle = Toggle.DISABLED
            private set

        fun duration(duration: Long) = apply { this.duration = duration }
        fun distanceMultiplier(distanceMultiplier: Long) = apply { this.distanceMultiplier = distanceMultiplier }
        fun landingAnimation(landingAnimation: Toggle) = apply { this.landingAnimation = landingAnimation }

        override fun build() = Hold(
            active,
            duration,
            distanceMultiplier,
            landingAnimation,
        )
    }
}
