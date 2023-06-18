package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.TrackAnimation
import io.luxus.adofai.lib.property.TrackDisappearAnimation

@EventType(jsonValue = "AnimateTrack", builderClass = AnimateTrack.Builder::class, single = true)
class AnimateTrack private constructor(
    active: Boolean?,
    val trackAnimation: TrackAnimation,
    val beatsAhead: Double,
    val trackDisappearAnimation: TrackDisappearAnimation,
    val beatsBehind: Double,
) : Action(AnimateTrack::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .trackAnimation(trackAnimation)
        .beatsAhead(beatsAhead)
        .trackDisappearAnimation(trackDisappearAnimation)
        .beatsBehind(beatsBehind)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var trackAnimation: TrackAnimation = TrackAnimation.NONE
            private set
        var beatsAhead: Double = 3.0
            private set
        var trackDisappearAnimation: TrackDisappearAnimation = TrackDisappearAnimation.NONE
            private set
        var beatsBehind: Double = 4.0
            private set

        fun trackAnimation(trackAnimation: TrackAnimation) = apply { this.trackAnimation = trackAnimation }
        fun beatsAhead(beatsAhead: Double) = apply { this.beatsAhead = beatsAhead }
        fun trackDisappearAnimation(trackDisappearAnimation: TrackDisappearAnimation) =
            apply { this.trackDisappearAnimation = trackDisappearAnimation }

        fun beatsBehind(beatsBehind: Double) = apply { this.beatsBehind = beatsBehind }

        override fun build() = AnimateTrack(
            active,
            trackAnimation,
            beatsAhead,
            trackDisappearAnimation,
            beatsBehind
        )

    }

}
