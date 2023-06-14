package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class GameSound(override val jsonValue: String) : JsonParseable {
    HITSOUND("Hitsound"),
    MIDSPIN("Midspin"),
}
