package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class TrackColorPulse(override val jsonValue: String) : JsonParseable {
    NONE("None"),
    FORWARD("Forward"),
    BACKWARD("Backward"),
}
