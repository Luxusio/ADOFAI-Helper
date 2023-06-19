package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.CameraRelativeTo
import io.luxus.adofai.lib.property.Ease

@EventType(jsonValue = "MoveCamera", builderClass = MoveCamera.Builder::class, single = false)
data class MoveCamera(
    override val active: Boolean?,
    val duration: Double,
    val relativeTo: CameraRelativeTo?,
    val position: Pair<Double?, Double?>?,
    val rotation: Double?,
    val zoom: Double?,
    val angleOffset: Double,
    val ease: Ease,
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
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this

        var duration: Double = 1.0
            private set
        var relativeTo: CameraRelativeTo? = CameraRelativeTo.PLAYER
            private set
        var position: Pair<Double?, Double?>? = Pair(null, null)
            private set
        var rotation: Double? = null
            private set
        var zoom: Double? = null
            private set
        var angleOffset: Double = 0.0
            private set
        var ease: Ease = Ease.LINEAR
            private set
        var eventTag: String = ""
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun relativeTo(relativeTo: CameraRelativeTo?) = apply { this.relativeTo = relativeTo }
        fun position(position: Pair<Double?, Double?>?) = apply { this.position = position }
        fun rotation(rotation: Double?) = apply { this.rotation = rotation }
        fun zoom(zoom: Double?) = apply { this.zoom = zoom }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun ease(ease: Ease) = apply { this.ease = ease }
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
            eventTag,
        )
    }
}
