package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.AlphaColor
import io.luxus.adofai.lib.property.DecorationRelativeTo
import io.luxus.adofai.lib.property.Toggle

class AddDecoration private constructor(
    active: Boolean?,
    val decorationImage: String,
    val position: Pair<Double, Double>,
    val relativeTo: DecorationRelativeTo,
    val pivotOffset: Pair<Double, Double>,
    val rotation: Double,
    val scale: Pair<Double, Double>,
    val tile: Pair<Long, Long>,
    val color: AlphaColor,
    val opacity: Double,
    val depth: Long,
    val parallax: Pair<Double, Double>,
    val tag: String,
    val imageSmoothing: Toggle,
    val components: String,
) : Action(AddDecoration::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .decorationImage(decorationImage)
        .position(position)
        .relativeTo(relativeTo)
        .pivotOffset(pivotOffset)
        .rotation(rotation)
        .scale(scale)
        .tile(tile)
        .color(color)
        .opacity(opacity)
        .depth(depth)
        .parallax(parallax)
        .tag(tag)
        .imageSmoothing(imageSmoothing)
        .components(components)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var decorationImage: String = ""
            private set
        var position: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var relativeTo: DecorationRelativeTo = DecorationRelativeTo.TILE
            private set
        var pivotOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var rotation: Double = 0.0
            private set
        var scale: Pair<Double, Double> = Pair(100.0, 100.0)
            private set
        var tile: Pair<Long, Long> = Pair(1L, 1L)
            private set
        var color: AlphaColor = AlphaColor.WHITE
            private set
        var opacity: Double = 100.0
            private set
        var depth: Long = 0L
            private set
        var parallax: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var tag: String = ""
            private set
        var imageSmoothing: Toggle = Toggle.ENABLED
            private set
        var components: String = ""
            private set

        fun decorationImage(decorationImage: String) = apply { this.decorationImage = decorationImage }
        fun position(position: Pair<Double, Double>) = apply { this.position = position }
        fun relativeTo(relativeTo: DecorationRelativeTo) = apply { this.relativeTo = relativeTo }
        fun pivotOffset(pivotOffset: Pair<Double, Double>) = apply { this.pivotOffset = pivotOffset }
        fun rotation(rotation: Double) = apply { this.rotation = rotation }
        fun scale(scale: Pair<Double, Double>) = apply { this.scale = scale }
        fun tile(tile: Pair<Long, Long>) = apply { this.tile = tile }
        fun color(color: AlphaColor) = apply { this.color = color }
        fun opacity(opacity: Double) = apply { this.opacity = opacity }
        fun depth(depth: Long) = apply { this.depth = depth }
        fun parallax(parallax: Pair<Double, Double>) = apply { this.parallax = parallax }
        fun tag(tag: String) = apply { this.tag = tag }
        fun imageSmoothing(imageSmoothing: Toggle) = apply { this.imageSmoothing = imageSmoothing }
        fun components(components: String) = apply { this.components = components }

        override fun build() = AddDecoration(
            active,
            decorationImage,
            position,
            relativeTo,
            pivotOffset,
            rotation,
            scale,
            tile,
            color,
            opacity,
            depth,
            parallax,
            tag,
            imageSmoothing,
            components,
        )
    }
}
