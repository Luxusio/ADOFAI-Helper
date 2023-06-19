package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class TrackColorType(override val jsonValue: String) : JsonParseable {
    SINGLE("Single"),
    STRIPES("Stripes"),
    GLOW("Glow"),
    BLINK("Blink"),
    SWITCH("Switch"),
    RAINBOW("Rainbow"),
    VOLUME("Volume"),
}
