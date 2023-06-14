package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.*

class ChangeTrack private constructor(
    active: Boolean?,
    val trackColorType: TrackColorType,
    val trackColor: AlphaColor,
    val secondaryTrackColor: AlphaColor,
    val trackColorAnimDuration: Double,
    val trackColorPulse: TrackColorPulse,
    val trackPulseLength: Long,
    val trackStyle: TrackStyle,
    val trackAnimation: TrackAnimation,
    val beatsAhead: Double,
    val trackDisappearAnimation: TrackDisappearAnimation,
    val beatsBehind: Double,
) : Action(ChangeTrack::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .trackColorType(trackColorType)
        .trackColor(trackColor)
        .secondaryTrackColor(secondaryTrackColor)
        .trackColorAnimDuration(trackColorAnimDuration)
        .trackColorPulse(trackColorPulse)
        .trackPulseLength(trackPulseLength)
        .trackStyle(trackStyle)
        .trackAnimation(trackAnimation)
        .beatsAhead(beatsAhead)
        .trackDisappearAnimation(trackDisappearAnimation)
        .beatsBehind(beatsBehind)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var trackColorType: TrackColorType = TrackColorType.SINGLE
            private set
        var trackColor: AlphaColor = AlphaColor.Builder().rgba("debb7b").build()
            private set
        var secondaryTrackColor: AlphaColor = AlphaColor.WHITE
            private set
        var trackColorAnimDuration: Double = 2.0
            private set
        var trackColorPulse: TrackColorPulse = TrackColorPulse.NONE
            private set
        var trackPulseLength: Long = 10L
            private set
        var trackStyle: TrackStyle = TrackStyle.STANDARD
            private set
        var trackAnimation: TrackAnimation = TrackAnimation.NONE
            private set
        var beatsAhead: Double = 3.0
            private set
        var trackDisappearAnimation: TrackDisappearAnimation = TrackDisappearAnimation.NONE
            private set
        var beatsBehind: Double = 4.0
            private set

        fun trackColorType(trackColorType: TrackColorType) = apply { this.trackColorType = trackColorType }
        fun trackColor(trackColor: AlphaColor) = apply { this.trackColor = trackColor }
        fun secondaryTrackColor(secondaryTrackColor: AlphaColor) =
            apply { this.secondaryTrackColor = secondaryTrackColor }

        fun trackColorAnimDuration(trackColorAnimDuration: Double) =
            apply { this.trackColorAnimDuration = trackColorAnimDuration }

        fun trackColorPulse(trackColorPulse: TrackColorPulse) = apply { this.trackColorPulse = trackColorPulse }
        fun trackPulseLength(trackPulseLength: Long) = apply { this.trackPulseLength = trackPulseLength }
        fun trackStyle(trackStyle: TrackStyle) = apply { this.trackStyle = trackStyle }
        fun trackAnimation(trackAnimation: TrackAnimation) = apply { this.trackAnimation = trackAnimation }
        fun beatsAhead(beatsAhead: Double) = apply { this.beatsAhead = beatsAhead }
        fun trackDisappearAnimation(trackDisappearAnimation: TrackDisappearAnimation) =
            apply { this.trackDisappearAnimation = trackDisappearAnimation }

        fun beatsBehind(beatsBehind: Double) = apply { this.beatsBehind = beatsBehind }

        override fun build() = ChangeTrack(
            active,
            trackColorType,
            trackColor,
            secondaryTrackColor,
            trackColorAnimDuration,
            trackColorPulse,
            trackPulseLength,
            trackStyle,
            trackAnimation,
            beatsAhead,
            trackDisappearAnimation,
            beatsBehind
        )
    }
}
