package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.TargetPlanet

@EventType(jsonValue = "ScalePlanets", builderClass = ScalePlanets.Builder::class, single = true)
data class ScalePlanets(
    override val active: Boolean?,
    val duration: Double,
    val targetPlanet: TargetPlanet,
    val scale: Double,
    val angleOffset: Double,
    val ease: Ease,
    val eventTag: String,
) : Action(ScalePlanets::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .duration(duration)
        .targetPlanet(targetPlanet)
        .scale(scale)
        .angleOffset(angleOffset)
        .ease(ease)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var duration: Double = 1.0
            private set
        var targetPlanet: TargetPlanet = TargetPlanet.FIRE_PLANET
            private set
        var scale: Double = 100.0
            private set
        var angleOffset: Double = 0.0
            private set
        var ease: Ease = Ease.LINEAR
            private set
        var eventTag: String = ""
            private set

        fun duration(duration: Double) = apply { this.duration = duration }
        fun targetPlanet(targetPlanet: TargetPlanet) = apply { this.targetPlanet = targetPlanet }
        fun scale(scale: Double) = apply { this.scale = scale }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun ease(ease: Ease) = apply { this.ease = ease }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = ScalePlanets(
            active,
            duration,
            targetPlanet,
            scale,
            angleOffset,
            ease,
            eventTag,
        )
    }
}
