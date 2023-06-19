package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.AlphaColor
import io.luxus.adofai.lib.property.DecorationRelativeTo
import io.luxus.adofai.lib.property.Font
import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "AddText", builderClass = AddText.Builder::class, single = false)
data class AddText(
    override val visible: Boolean?,
    override val floor: Long?,
    val decText: String,
    val font: Font,
    val position: Pair<Double, Double>,
    val relativeTo: DecorationRelativeTo,
    val pivotOffset: Pair<Double, Double>,
    val rotation: Double,
    val lockRotation: Toggle,
    val scale: Pair<Double, Double>,
    val lockScale: Toggle,
    val color: AlphaColor,
    val opacity: Double,
    val depth: Long,
    val parallax: Pair<Double, Double>,
    val parallaxOffset: Pair<Double, Double>,
    val tag: String,
) : Decoration(AddText::class.java, visible, floor) {

    override fun toBuilder() = Builder()
        .visible(visible)
        .floor(floor)
        .decText(decText)
        .font(font)
        .position(position)
        .relativeTo(relativeTo)
        .pivotOffset(pivotOffset)
        .rotation(rotation)
        .lockRotation(lockRotation)
        .scale(scale)
        .lockScale(lockScale)
        .color(color)
        .opacity(opacity)
        .depth(depth)
        .parallax(parallax)
        .parallaxOffset(parallaxOffset)
        .tag(tag)

    class Builder : Decoration.Builder<Builder>() {
        override val self = this
        var decText: String = "text"
            private set
        var font: Font = Font.DEFAULT
            private set
        var position: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var relativeTo: DecorationRelativeTo = DecorationRelativeTo.TILE
            private set
        var pivotOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var rotation: Double = 0.0
            private set
        var lockRotation: Toggle = Toggle.DISABLED
            private set
        var scale: Pair<Double, Double> = Pair(100.0, 100.0)
            private set
        var lockScale: Toggle = Toggle.DISABLED
            private set
        var color: AlphaColor = AlphaColor.WHITE
            private set
        var opacity: Double = 100.0
            private set
        var depth: Long = -1L
            private set
        var parallax: Pair<Double, Double> = Pair(-1.0, -1.0)
            private set
        var parallaxOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var tag: String = ""
            private set

        fun decText(decText: String) = apply { this.decText = decText }
        fun font(font: Font) = apply { this.font = font }
        fun position(position: Pair<Double, Double>) = apply { this.position = position }
        fun relativeTo(relativeTo: DecorationRelativeTo) = apply { this.relativeTo = relativeTo }
        fun pivotOffset(pivotOffset: Pair<Double, Double>) = apply { this.pivotOffset = pivotOffset }
        fun rotation(rotation: Double) = apply { this.rotation = rotation }
        fun lockRotation(lockRotation: Toggle) = apply { this.lockRotation = lockRotation }
        fun scale(scale: Pair<Double, Double>) = apply { this.scale = scale }
        fun lockScale(lockScale: Toggle) = apply { this.lockScale = lockScale }
        fun color(color: AlphaColor) = apply { this.color = color }
        fun opacity(opacity: Double) = apply { this.opacity = opacity }
        fun depth(depth: Long) = apply { this.depth = depth }
        fun parallax(parallax: Pair<Double, Double>) = apply { this.parallax = parallax }
        fun parallaxOffset(parallaxOffset: Pair<Double, Double>) = apply { this.parallaxOffset = parallaxOffset }
        fun tag(tag: String) = apply { this.tag = tag }

        override fun build() = AddText(
            visible,
            floor,
            decText,
            font,
            position,
            relativeTo,
            pivotOffset,
            rotation,
            lockRotation,
            scale,
            lockScale,
            color,
            opacity,
            depth,
            parallax,
            parallaxOffset,
            tag,
        )
    }
}
