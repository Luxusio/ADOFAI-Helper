package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.BGDisplayModeType
import io.luxus.adofai.lib.property.Color
import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "CustomBackground", builderClass = CustomBackground.Builder::class, single = false)
data class CustomBackground(
    override val active: Boolean?,
    val color: Color,
    val bgImage: String,
    val imageColor: Color,
    val parallax: Pair<Double, Double>,
    val bgDisplayMode: BGDisplayModeType,
    val imageSmoothing: Toggle,
    val lockRot: Toggle,
    val loopBG: Toggle,
    val scalingRatio: Long,
    val unscaledSize: Long,
    val angleOffset: Double,
    val eventTag: String,
) : Action(CustomBackground::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .color(color)
        .bgImage(bgImage)
        .imageColor(imageColor)
        .parallax(parallax)
        .bgDisplayMode(bgDisplayMode)
        .imageSmoothing(imageSmoothing)
        .lockRot(lockRot)
        .loopBG(loopBG)
        .scalingRatio(scalingRatio)
        .unscaledSize(unscaledSize)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var color: Color = Color.BLACK
            private set
        var bgImage: String = ""
            private set
        var imageColor: Color = Color.WHITE
            private set
        var parallax: Pair<Double, Double> = Pair(100.0, 100.0)
            private set
        var bgDisplayMode: BGDisplayModeType = BGDisplayModeType.FIT_TO_SCREEN
            private set
        var imageSmoothing: Toggle = Toggle.ENABLED
            private set
        var lockRot: Toggle = Toggle.DISABLED
            private set
        var loopBG: Toggle = Toggle.DISABLED
            private set
        var scalingRatio: Long = 100L
            private set
        var unscaledSize: Long = 100L
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun color(color: Color) = apply { this.color = color }
        fun bgImage(bgImage: String) = apply { this.bgImage = bgImage }
        fun imageColor(imageColor: Color) = apply { this.imageColor = imageColor }
        fun parallax(parallax: Pair<Double, Double>) = apply { this.parallax = parallax }
        fun bgDisplayMode(bgDisplayMode: BGDisplayModeType) = apply { this.bgDisplayMode = bgDisplayMode }
        fun imageSmoothing(imageSmoothing: Toggle) = apply { this.imageSmoothing = imageSmoothing }
        fun lockRot(lockRot: Toggle) = apply { this.lockRot = lockRot }
        fun loopBG(loopBG: Toggle) = apply { this.loopBG = loopBG }
        fun scalingRatio(scalingRatio: Long) = apply { this.scalingRatio = scalingRatio }
        fun unscaledSize(unscaledSize: Long) = apply { this.unscaledSize = unscaledSize }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = CustomBackground(
            active,
            color,
            bgImage,
            imageColor,
            parallax,
            bgDisplayMode,
            imageSmoothing,
            lockRot,
            loopBG,
            scalingRatio,
            unscaledSize,
            angleOffset,
            eventTag,
        )
    }
}
