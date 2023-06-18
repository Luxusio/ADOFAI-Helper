package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.TilePosition
import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "MoveTrack", builderClass = MoveTrack.Builder::class, single = false)
class MoveTrack private constructor(
    active: Boolean?,
    val startTile: Pair<Long, TilePosition>,
    val endTile: Pair<Long, TilePosition>,
    val gapLength: Long,
    val duration: Double,
    val positionOffset: Pair<Double, Double>,
    val rotationOffset: Double?,
    val scale: Pair<Double, Double>?,
    val opacity: Double?,
    val angleOffset: Double,
    val ease: Ease,
    val maxVfxOnly: Toggle,
    val eventTag: String
) : Action(MoveTrack::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .startTile(startTile)
        .endTile(endTile)
        .gapLength(gapLength)
        .duration(duration)
        .positionOffset(positionOffset)
        .rotationOffset(rotationOffset)
        .scale(scale)
        .opacity(opacity)
        .angleOffset(angleOffset)
        .ease(ease)
        .maxVfxOnly(maxVfxOnly)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var startTile = Pair(0L, TilePosition.THIS_TILE)
            private set
        var endTile = Pair(0L, TilePosition.THIS_TILE)
            private set
        var gapLength: Long = 0
            private set
        var duration = 1.0
            private set
        var positionOffset = Pair(0.0, 0.0)
            private set
        var rotationOffset: Double? = null
            private set
        var scale: Pair<Double, Double>? = null
            private set
        var opacity: Double? = null
            private set
        var angleOffset = 0.0
            private set
        var ease = Ease.LINEAR
            private set
        var maxVfxOnly = Toggle.DISABLED
            private set
        var eventTag = ""
            private set

        fun startTile(startTile: Pair<Long, TilePosition>) = apply { this.startTile = startTile }
        fun endTile(endTile: Pair<Long, TilePosition>) = apply { this.endTile = endTile }
        fun gapLength(gapLength: Long) = apply { this.gapLength = gapLength }
        fun duration(duration: Double) = apply { this.duration = duration }
        fun positionOffset(positionOffset: Pair<Double, Double>) = apply { this.positionOffset = positionOffset }
        fun rotationOffset(rotationOffset: Double?) = apply { this.rotationOffset = rotationOffset }
        fun scale(scale: Pair<Double, Double>?) = apply { this.scale = scale }
        fun opacity(opacity: Double?) = apply { this.opacity = opacity }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun ease(ease: Ease) = apply { this.ease = ease }
        fun maxVfxOnly(maxVfxOnly: Toggle) = apply { this.maxVfxOnly = maxVfxOnly }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = MoveTrack(
            active,
            startTile,
            endTile,
            gapLength,
            duration,
            positionOffset,
            rotationOffset,
            scale,
            opacity,
            angleOffset,
            ease,
            maxVfxOnly,
            eventTag
        )
    }
}
