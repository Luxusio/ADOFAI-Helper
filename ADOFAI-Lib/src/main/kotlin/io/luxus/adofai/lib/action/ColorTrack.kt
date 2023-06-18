package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.AlphaColor
import io.luxus.adofai.lib.property.TrackColorPulse
import io.luxus.adofai.lib.property.TrackColorType
import io.luxus.adofai.lib.property.TrackStyle

@EventType(jsonValue = "ColorTrack", builderClass = ColorTrack.Builder::class, single = true)
class ColorTrack private constructor(
    active: Boolean?,
    val trackColorType: TrackColorType,
    val trackColor: AlphaColor,
    val secondaryTrackColor: AlphaColor,
    val trackColorAnimDuration: Double,
    val trackColorPulse: TrackColorPulse,
    val trackPulseLength: Long,
    val trackStyle: TrackStyle,
    val trackTexture: String,
    val trackTextureScale: Double,
    val trackGlowIntensity: Double,
) : Action(ColorTrack::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .trackColorType(trackColorType)
        .trackColor(trackColor)
        .secondaryTrackColor(secondaryTrackColor)
        .trackColorAnimDuration(trackColorAnimDuration)
        .trackColorPulse(trackColorPulse)
        .trackPulseLength(trackPulseLength)
        .trackStyle(trackStyle)
        .trackTexture(trackTexture)
        .trackTextureScale(trackTextureScale)
        .trackGlowIntensity(trackGlowIntensity)

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
        var trackTexture: String = ""
            private set
        var trackTextureScale: Double = 1.0
            private set
        var trackGlowIntensity: Double = 100.0
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
        fun trackTexture(trackTexture: String) = apply { this.trackTexture = trackTexture }
        fun trackTextureScale(trackTextureScale: Double) = apply { this.trackTextureScale = trackTextureScale }
        fun trackGlowIntensity(trackGlowIntensity: Double) = apply { this.trackGlowIntensity = trackGlowIntensity }

        override fun build() = ColorTrack(
            active,
            trackColorType,
            trackColor,
            secondaryTrackColor,
            trackColorAnimDuration,
            trackColorPulse,
            trackPulseLength,
            trackStyle,
            trackTexture,
            trackTextureScale,
            trackGlowIntensity,
        )
    }
}
