package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.CameraRelativeTo
import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.Toggle

class MoveCamera private constructor(
    active: Boolean?,
    val duration: Double,
    val relativeTo: CameraRelativeTo,
    val position: Pair<Double, Double>,
    val rotation: Double,
    val zoom: Long,
    val angleOffset: Double,
    val ease: Ease,
    val dontDisable: Toggle,
    val minVfxOnly: Toggle,
    val eventTag: String,
) : Action(MoveCamera::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .relativeTo(relativeTo)
        .position(position)
        .rotation(rotation)
        .zoom(zoom)
        .angleOffset(angleOffset)
        .ease(ease)
        .dontDisable(dontDisable)
        .minVfxOnly(minVfxOnly)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this

        var duration: Double = 1.0
            private set
        var relativeTo: CameraRelativeTo = CameraRelativeTo.PLAYER
            private set
        var position: Pair<Double, Double> = Pair(0.0, 0.0)
            private set
        var rotation: Double = 0.0
            private set
        var zoom: Long = 100L
            private set
        var angleOffset: Double = 0.0
            private set
        var ease: Ease = Ease.LINEAR
            private set
        var dontDisable: Toggle = Toggle.DISABLED
            private set
        var minVfxOnly: Toggle = Toggle.DISABLED
            private set
        var eventTag: String = ""
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun relativeTo(relativeTo: CameraRelativeTo) = apply { this.relativeTo = relativeTo }
        fun position(position: Pair<Double, Double>) = apply { this.position = position }
        fun rotation(rotation: Double) = apply { this.rotation = rotation }
        fun zoom(zoom: Long) = apply { this.zoom = zoom }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun ease(ease: Ease) = apply { this.ease = ease }
        fun dontDisable(dontDisable: Toggle) = apply { this.dontDisable = dontDisable }
        fun minVfxOnly(minVfxOnly: Toggle) = apply { this.minVfxOnly = minVfxOnly }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = MoveCamera(
            active,
            duration,
            relativeTo,
            position,
            rotation,
            zoom,
            angleOffset,
            ease,
            dontDisable,
            minVfxOnly,
            eventTag,
        )
    }
}
