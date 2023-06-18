package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.AlphaColor
import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.MaskingType
import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "MoveDecorations", builderClass = MoveDecorations.Builder::class, single = false)
class MoveDecorations private constructor(
    active: Boolean?,
    val duration: Double,
    val tag: String,
    val decorationImage: String?,
    val positionOffset: Pair<Double?, Double?>,
    val pivotOffset: Pair<Double?, Double?>,
    val rotationOffset: Double?,
    val scale: Pair<Double, Double>?,
    val color: AlphaColor?,
    val opacity: Double?,
    val depth: Long?,
    val parallax: Pair<Double, Double>?,
    val parallaxOffset: Pair<Double?, Double?>?,
    val angleOffset: Double,
    val ease: Ease,
    val eventTag: String,
    val maskingType: MaskingType?,
    val useMaskingDepth: Toggle?,
    val maskingFrontDepth: Long?,
    val maskingBackDepth: Long?,
) : Action(MoveDecorations::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .tag(tag)
        .decorationImage(decorationImage)
        .positionOffset(positionOffset)
        .pivotOffset(pivotOffset)
        .rotationOffset(rotationOffset)
        .scale(scale)
        .color(color)
        .opacity(opacity)
        .depth(depth)
        .parallax(parallax)
        .parallaxOffset(parallaxOffset)
        .angleOffset(angleOffset)
        .ease(ease)
        .eventTag(eventTag)
        .maskingType(maskingType)
        .useMaskingDepth(useMaskingDepth)
        .maskingFrontDepth(maskingFrontDepth)
        .maskingBackDepth(maskingBackDepth)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var duration: Double = 1.0
            private set
        var tag: String = ""
            private set
        var decorationImage: String? = null
            private set
        var positionOffset: Pair<Double?, Double?> = Pair(0.0, 0.0)
            private set
        var pivotOffset: Pair<Double?, Double?> = Pair(null, null)
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
        var parallaxOffset: Pair<Double?, Double?>? = null
            private set
        var angleOffset: Double = 0.0
            private set
        var ease: Ease = Ease.LINEAR
            private set
        var eventTag: String = ""
            private set
        var maskingType: MaskingType? = null
            private set
        var useMaskingDepth: Toggle? = null
            private set
        var maskingFrontDepth: Long? = null
            private set
        var maskingBackDepth: Long? = null
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun tag(tag: String) = apply { this.tag = tag }
        fun decorationImage(decorationImage: String?) = apply { this.decorationImage = decorationImage }
        fun positionOffset(positionOffset: Pair<Double?, Double?>) = apply { this.positionOffset = positionOffset }
        fun pivotOffset(pivotOffset: Pair<Double?, Double?>) = apply { this.pivotOffset = pivotOffset }
        fun rotationOffset(rotationOffset: Double?) = apply { this.rotationOffset = rotationOffset }
        fun scale(scale: Pair<Double, Double>?) = apply { this.scale = scale }
        fun color(color: AlphaColor?) = apply { this.color = color }
        fun opacity(opacity: Double?) = apply { this.opacity = opacity }
        fun depth(depth: Long?) = apply { this.depth = depth }
        fun parallax(parallax: Pair<Double, Double>?) = apply { this.parallax = parallax }
        fun parallaxOffset(parallaxOffset: Pair<Double?, Double?>?) = apply { this.parallaxOffset = parallaxOffset }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun ease(ease: Ease) = apply { this.ease = ease }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }
        fun maskingType(maskingType: MaskingType?) = apply { this.maskingType = maskingType }
        fun useMaskingDepth(useMaskingDepth: Toggle?) = apply { this.useMaskingDepth = useMaskingDepth }
        fun maskingFrontDepth(maskingFrontDepth: Long?) = apply { this.maskingFrontDepth = maskingFrontDepth }
        fun maskingBackDepth(maskingBackDepth: Long?) = apply { this.maskingBackDepth = maskingBackDepth }

        override fun build() = MoveDecorations(
            active,
            duration,
            tag,
            decorationImage,
            positionOffset,
            pivotOffset,
            rotationOffset,
            scale,
            color,
            opacity,
            depth,
            parallax,
            parallaxOffset,
            angleOffset,
            ease,
            eventTag,
            maskingType,
            useMaskingDepth,
            maskingFrontDepth,
            maskingBackDepth,
        )
    }
}
