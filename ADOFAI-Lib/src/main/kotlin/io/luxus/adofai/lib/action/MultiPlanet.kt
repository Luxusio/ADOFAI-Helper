package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.util.requireMin

class MultiPlanet private constructor(
    active: Boolean?,
    val planets: Long,
) : Action(MultiPlanet::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .planets(planets)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var planets: Long = 2L
            private set

        fun planets(planets: Long) = apply { this.planets = planets.requireMin(2L) }

        override fun build() = MultiPlanet(
            active,
            planets,
        )
    }
}
