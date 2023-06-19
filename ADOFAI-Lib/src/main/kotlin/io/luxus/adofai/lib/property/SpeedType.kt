package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class SpeedType(override val jsonValue: String) : JsonParseable {
    BPM("Bpm"),
    MULTIPLIER("Multiplier"),
}
