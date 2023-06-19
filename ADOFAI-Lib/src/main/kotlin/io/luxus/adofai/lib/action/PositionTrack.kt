package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.TilePosition
import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "PositionTrack", builderClass = PositionTrack.Builder::class, single = true)
data class PositionTrack(
    override val active: Boolean?,
    val positionOffset: Pair<Double, Double>,
    val relativeTo: Pair<Long, TilePosition>,
    val rotation: Double,
    val scale: Double,
    val opacity: Double,
    val justThisTile: Toggle,
    val editorOnly: Toggle,
    val stickToFloors: Toggle?,
) : Action(PositionTrack::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .positionOffset(positionOffset)
        .relativeTo(relativeTo)
        .rotation(rotation)
        .scale(scale)
        .opacity(opacity)
        .justThisTile(justThisTile)
        .editorOnly(editorOnly)
        .stickToFloors(stickToFloors)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var positionOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var relativeTo: Pair<Long, TilePosition> = Pair(0, TilePosition.THIS_TILE)
            private set
        var rotation: Double = 0.0
            private set
        var scale: Double = 100.0
            private set
        var opacity: Double = 100.0
            private set
        var justThisTile: Toggle = Toggle.DISABLED
            private set
        var editorOnly: Toggle = Toggle.DISABLED
            private set
        var stickToFloors: Toggle? = null
            private set

        fun positionOffset(positionOffset: Pair<Double, Double>) = apply { this.positionOffset = positionOffset }
        fun relativeTo(relativeTo: Pair<Long, TilePosition>) = apply { this.relativeTo = relativeTo }
        fun rotation(rotation: Double) = apply { this.rotation = rotation }
        fun scale(scale: Double) = apply { this.scale = scale }
        fun opacity(opacity: Double) = apply { this.opacity = opacity }
        fun justThisTile(justThisTile: Toggle) = apply { this.justThisTile = justThisTile }
        fun editorOnly(editorOnly: Toggle) = apply { this.editorOnly = editorOnly }
        fun stickToFloors(stickToFloors: Toggle?) = apply { this.stickToFloors = stickToFloors }

        override fun build() = PositionTrack(
            active,
            positionOffset,
            relativeTo,
            rotation,
            scale,
            opacity,
            justThisTile,
            editorOnly,
            stickToFloors
        )
    }
}
