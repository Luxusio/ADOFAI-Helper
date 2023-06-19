package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class HoldMidSoundType(override val jsonValue: String) : JsonParseable {
    ONCE("Once"),
    REPEAT("Repeat"),
}
