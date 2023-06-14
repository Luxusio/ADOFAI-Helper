package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Toggle

class PositionTrack private constructor(
    active: Boolean?,
    val positionOffset: Pair<Double, Double>,
    val rotation: Double,
    val scale: Double,
    val opacity: Double,
    val editorOnly: Toggle,
) : Action(PositionTrack::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .positionOffset(positionOffset)
        .rotation(rotation)
        .scale(scale)
        .opacity(opacity)
        .editorOnly(editorOnly)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var positionOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var rotation: Double = 0.0
            private set
        var scale: Double = 100.0
            private set
        var opacity: Double = 100.0
            private set
        var editorOnly: Toggle = Toggle.DISABLED
            private set

        fun positionOffset(positionOffset: Pair<Double, Double>) = apply { this.positionOffset = positionOffset }
        fun rotation(rotation: Double) = apply { this.rotation = rotation }
        fun scale(scale: Double) = apply { this.scale = scale }
        fun opacity(opacity: Double) = apply { this.opacity = opacity }
        fun editorOnly(editorOnly: Toggle) = apply { this.editorOnly = editorOnly }

        override fun build() = PositionTrack(
            active,
            positionOffset,
            rotation,
            scale,
            opacity,
            editorOnly,
        )
    }
}
