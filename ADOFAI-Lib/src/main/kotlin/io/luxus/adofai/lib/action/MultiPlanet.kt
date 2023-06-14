package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Planets

class MultiPlanet private constructor(
    active: Boolean?,
    val planets: Planets,
) : Action(MultiPlanet::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .planets(planets)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var planets: Planets = Planets.TWO_PLANETS
            private set

        fun planets(planets: Planets) = apply { this.planets = planets }

        override fun build() = MultiPlanet(
            active,
            planets,
        )
    }
}
