package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Ease

class SetPlanetRotation private constructor(
    active: Boolean?,
    val ease: Ease,
    val easeParts: Long,
) : Action(SetPlanetRotation::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .ease(ease)
        .easeParts(easeParts)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var ease: Ease = Ease.LINEAR
            private set
        var easeParts: Long = 1L
            private set

        fun ease(ease: Ease) = apply { this.ease = ease }
        fun easeParts(easeParts: Long) = apply { this.easeParts = easeParts }

        override fun build() = SetPlanetRotation(
            active,
            ease,
            easeParts,
        )
    }
}
