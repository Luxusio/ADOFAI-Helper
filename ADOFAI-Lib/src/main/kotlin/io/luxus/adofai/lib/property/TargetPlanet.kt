package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class TargetPlanet(override val jsonValue: String) : JsonParseable {
    FIRE_PLANET("FirePlanet"),
    ICE_PLANET("IcePlanet"),
    BOTH("Both")
}
