package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.*

@EventType(jsonValue = "AddDecoration", builderClass = AddDecoration.Builder::class, single = false)
class AddDecoration private constructor(
    active: Boolean?,
    visible: Boolean?,
    floor: Long?,
    val decorationImage: String,
    val position: Pair<Double, Double>,
    val relativeTo: DecorationRelativeTo,
    val pivotOffset: Pair<Double, Double>,
    val rotation: Double,
    val lockRotation: Toggle,
    val scale: Pair<Double, Double>,
    val lockScale: Toggle,
    val tile: Pair<Long, Long>,
    val color: AlphaColor,
    val opacity: Double,
    val depth: Long,
    val parallax: Pair<Double, Double>,
    val parallaxOffset: Pair<Double, Double>,
    val tag: String,
    val imageSmoothing: Toggle,
    val blendMode: BlendMode,
    val maskingType: MaskingType,
    val useMaskingDepth: Toggle,
    val maskingFrontDepth: Long,
    val maskingBackDepth: Long,
    val failHitbox: Toggle,
    val failHitboxType: FailHitboxType,
    val failHitboxScale: Pair<Double, Double>,
    val failHitboxOffset: Pair<Double, Double>,
    val failHitboxRotation: Double,
    val components: String,
) : Decoration(AddDecoration::class.java, active, visible, floor) {

    override fun toBuilder() = Builder()
        .active(active)
        .visible(visible)
        .floor(floor)
        .decorationImage(decorationImage)
        .position(position)
        .relativeTo(relativeTo)
        .pivotOffset(pivotOffset)
        .rotation(rotation)
        .lockRotation(lockRotation)
        .scale(scale)
        .lockScale(lockScale)
        .tile(tile)
        .color(color)
        .opacity(opacity)
        .depth(depth)
        .parallax(parallax)
        .parallaxOffset(parallaxOffset)
        .tag(tag)
        .imageSmoothing(imageSmoothing)
        .blendMode(blendMode)
        .maskingType(maskingType)
        .useMaskingDepth(useMaskingDepth)
        .maskingFrontDepth(maskingFrontDepth)
        .maskingBackDepth(maskingBackDepth)
        .failHitbox(failHitbox)
        .failHitboxType(failHitboxType)
        .failHitboxScale(failHitboxScale)
        .failHitboxOffset(failHitboxOffset)
        .failHitboxRotation(failHitboxRotation)
        .components(components)

    class Builder : Decoration.Builder<Builder>() {
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
        var lockRotation: Toggle = Toggle.DISABLED
            private set
        var scale: Pair<Double, Double> = Pair(100.0, 100.0)
            private set
        var lockScale: Toggle = Toggle.DISABLED
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
        var parallaxOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var tag: String = ""
            private set
        var imageSmoothing: Toggle = Toggle.ENABLED
            private set
        var blendMode: BlendMode = BlendMode.NONE
            private set
        var maskingType: MaskingType = MaskingType.NONE
            private set
        var useMaskingDepth: Toggle = Toggle.DISABLED
            private set
        var maskingFrontDepth: Long = -1L
            private set
        var maskingBackDepth: Long = -1L
            private set
        var failHitbox: Toggle = Toggle.DISABLED
            private set
        var failHitboxType: FailHitboxType = FailHitboxType.BOX
            private set
        var failHitboxScale: Pair<Double, Double> = Pair(100.0, 100.0)
            private set
        var failHitboxOffset: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var failHitboxRotation: Double = 0.0
            private set
        var components: String = ""
            private set

        fun decorationImage(decorationImage: String) = apply { this.decorationImage = decorationImage }
        fun position(position: Pair<Double, Double>) = apply { this.position = position }
        fun relativeTo(relativeTo: DecorationRelativeTo) = apply { this.relativeTo = relativeTo }
        fun pivotOffset(pivotOffset: Pair<Double, Double>) = apply { this.pivotOffset = pivotOffset }
        fun rotation(rotation: Double) = apply { this.rotation = rotation }
        fun lockRotation(lockRotation: Toggle) = apply { this.lockRotation = lockRotation }
        fun scale(scale: Pair<Double, Double>) = apply { this.scale = scale }
        fun lockScale(lockScale: Toggle) = apply { this.lockScale = lockScale }
        fun tile(tile: Pair<Long, Long>) = apply { this.tile = tile }
        fun color(color: AlphaColor) = apply { this.color = color }
        fun opacity(opacity: Double) = apply { this.opacity = opacity }
        fun depth(depth: Long) = apply { this.depth = depth }
        fun parallax(parallax: Pair<Double, Double>) = apply { this.parallax = parallax }
        fun parallaxOffset(parallaxOffset: Pair<Double, Double>) = apply { this.parallaxOffset = parallaxOffset }
        fun tag(tag: String) = apply { this.tag = tag }
        fun imageSmoothing(imageSmoothing: Toggle) = apply { this.imageSmoothing = imageSmoothing }
        fun blendMode(blendMode: BlendMode) = apply { this.blendMode = blendMode }
        fun maskingType(maskingType: MaskingType) = apply { this.maskingType = maskingType }
        fun useMaskingDepth(useMaskingDepth: Toggle) = apply { this.useMaskingDepth = useMaskingDepth }
        fun maskingFrontDepth(maskingFrontDepth: Long) = apply { this.maskingFrontDepth = maskingFrontDepth }
        fun maskingBackDepth(maskingBackDepth: Long) = apply { this.maskingBackDepth = maskingBackDepth }
        fun failHitbox(failHitbox: Toggle) = apply { this.failHitbox = failHitbox }
        fun failHitboxType(failHitboxType: FailHitboxType) = apply { this.failHitboxType = failHitboxType }
        fun failHitboxScale(failHitboxScale: Pair<Double, Double>) = apply { this.failHitboxScale = failHitboxScale }
        fun failHitboxOffset(failHitboxOffset: Pair<Double, Double>) =
            apply { this.failHitboxOffset = failHitboxOffset }

        fun failHitboxRotation(failHitboxRotation: Double) = apply { this.failHitboxRotation = failHitboxRotation }
        fun components(components: String) = apply { this.components = components }

        override fun build() = AddDecoration(
            active,
            visible,
            floor,
            decorationImage,
            position,
            relativeTo,
            pivotOffset,
            rotation,
            lockRotation,
            scale,
            lockScale,
            tile,
            color,
            opacity,
            depth,
            parallax,
            parallaxOffset,
            tag,
            imageSmoothing,
            blendMode,
            maskingType,
            useMaskingDepth,
            maskingFrontDepth,
            maskingBackDepth,
            failHitbox,
            failHitboxType,
            failHitboxScale,
            failHitboxOffset,
            failHitboxRotation,
            components,
        )
    }
}
