package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.AlphaColor
import io.luxus.adofai.lib.property.Ease

class MoveDecorations private constructor(
    active: Boolean?,
    val duration: Double,
    val tag: String,
    val decorationImage: String?,
    val positionOffset: Pair<Double, Double>,
    val rotationOffset: Double?,
    val scale: Pair<Double, Double>?,
    val color: AlphaColor?,
    val opacity: Double?,
    val depth: Long?,
    val parallax: Pair<Double, Double>?,
    val angleOffset: Double,
    val ease: Ease,
    val eventTag: String,
) : Action(MoveDecorations::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .tag(tag)
        .decorationImage(decorationImage)
        .positionOffset(positionOffset)
        .rotationOffset(rotationOffset)
        .scale(scale)
        .color(color)
        .opacity(opacity)
        .depth(depth)
        .parallax(parallax)
        .angleOffset(angleOffset)
        .ease(ease)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var duration: Double = 1.0
            private set
        var tag: String = ""
            private set
        var decorationImage: String? = null
            private set
        var positionOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var rotationOffset: Double? = null
            private set
        var scale: Pair<Double, Double>? = null
            private set
        var color: AlphaColor? = null
            private set
        var opacity: Double? = null
            private set
        var depth: Long? = null
            private set
        var parallax: Pair<Double, Double>? = null
            private set
        var angleOffset: Double = 0.0
            private set
        var ease: Ease = Ease.LINEAR
            private set
        var eventTag: String = ""
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun tag(tag: String) = apply { this.tag = tag }
        fun decorationImage(decorationImage: String?) = apply { this.decorationImage = decorationImage }
        fun positionOffset(positionOffset: Pair<Double, Double>) = apply { this.positionOffset = positionOffset }
        fun rotationOffset(rotationOffset: Double?) = apply { this.rotationOffset = rotationOffset }
        fun scale(scale: Pair<Double, Double>?) = apply { this.scale = scale }
        fun color(color: AlphaColor?) = apply { this.color = color }
        fun opacity(opacity: Double?) = apply { this.opacity = opacity }
        fun depth(depth: Long?) = apply { this.depth = depth }
        fun parallax(parallax: Pair<Double, Double>?) = apply { this.parallax = parallax }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun ease(ease: Ease) = apply { this.ease = ease }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = MoveDecorations(
            active,
            duration,
            tag,
            decorationImage,
            positionOffset,
            rotationOffset,
            scale,
            color,
            opacity,
            depth,
            parallax,
            angleOffset,
            ease,
            eventTag,
        )
    }
}
