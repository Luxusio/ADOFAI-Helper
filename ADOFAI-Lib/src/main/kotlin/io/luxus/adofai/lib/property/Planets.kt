package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class Planets(override val jsonValue: String) : JsonParseable {
    TWO_PLANETS("TwoPlanets"),
    THREE_PLANETS("ThreePlanets"),
}
