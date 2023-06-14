package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.*

class RecolorTrack private constructor(
    active: Boolean?,
    val startTile: Pair<Long, TilePosition>,
    val endTile: Pair<Long, TilePosition>,
    val trackColorType: TrackColorType,
    val trackColor: AlphaColor,
    val secondaryTrackColor: AlphaColor,
    val trackColorAnimDuration: Double,
    val trackColorPulse: TrackColorPulse,
    val trackPulseLength: Long,
    val trackStyle: TrackStyle,
    val angleOffset: Double,
    val eventTag: String
) : Action(RecolorTrack::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .startTile(startTile)
        .endTile(endTile)
        .trackColorType(trackColorType)
        .trackColor(trackColor)
        .secondaryTrackColor(secondaryTrackColor)
        .trackColorAnimDuration(trackColorAnimDuration)
        .trackColorPulse(trackColorPulse)
        .trackPulseLength(trackPulseLength)
        .trackStyle(trackStyle)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var startTile: Pair<Long, TilePosition> = Pair(0L, TilePosition.THIS_TILE)
            private set
        var endTile: Pair<Long, TilePosition> = Pair(0L, TilePosition.THIS_TILE)
            private set
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
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""

        fun startTile(startTile: Pair<Long, TilePosition>) = apply { this.startTile = startTile }
        fun endTile(endTile: Pair<Long, TilePosition>) = apply { this.endTile = endTile }
        fun trackColorType(trackColorType: TrackColorType) = apply { this.trackColorType = trackColorType }
        fun trackColor(trackColor: AlphaColor) = apply { this.trackColor = trackColor }
        fun secondaryTrackColor(secondaryTrackColor: AlphaColor) =
            apply { this.secondaryTrackColor = secondaryTrackColor }

        fun trackColorAnimDuration(trackColorAnimDuration: Double) =
            apply { this.trackColorAnimDuration = trackColorAnimDuration }

        fun trackColorPulse(trackColorPulse: TrackColorPulse) = apply { this.trackColorPulse = trackColorPulse }
        fun trackPulseLength(trackPulseLength: Long) = apply { this.trackPulseLength = trackPulseLength }
        fun trackStyle(trackStyle: TrackStyle) = apply { this.trackStyle = trackStyle }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = RecolorTrack(
            active,
            startTile,
            endTile,
            trackColorType,
            trackColor,
            secondaryTrackColor,
            trackColorAnimDuration,
            trackColorPulse,
            trackPulseLength,
            trackStyle,
            angleOffset,
            eventTag
        )
    }
}
