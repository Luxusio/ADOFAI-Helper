package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class Planets(override val jsonValue: String, val count: Long) : JsonParseable {
    TWO_PLANETS("TwoPlanets", 2L),
    THREE_PLANETS("ThreePlanets", 3L),
}
