package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Ease
import io.luxus.adofai.lib.property.EasePartBehavior

@EventType(jsonValue = "SetPlanetRotation", builderClass = SetPlanetRotation.Builder::class, single = true)
class SetPlanetRotation private constructor(
    active: Boolean?,
    val ease: Ease,
    val easeParts: Long,
    val easePartBehavior: EasePartBehavior,
) : Action(SetPlanetRotation::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .ease(ease)
        .easeParts(easeParts)
        .easePartBehavior(easePartBehavior)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var ease: Ease = Ease.LINEAR
            private set
        var easeParts: Long = 1L
            private set
        var easePartBehavior: EasePartBehavior = EasePartBehavior.MIRROR
            private set

        fun ease(ease: Ease) = apply { this.ease = ease }
        fun easeParts(easeParts: Long) = apply { this.easeParts = easeParts }
        fun easePartBehavior(easePartBehavior: EasePartBehavior) = apply { this.easePartBehavior = easePartBehavior }

        override fun build() = SetPlanetRotation(
            active,
            ease,
            easeParts,
            easePartBehavior,
        )
    }
}
