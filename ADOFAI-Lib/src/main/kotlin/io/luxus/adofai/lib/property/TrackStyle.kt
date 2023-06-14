package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class TrackStyle(override val jsonValue: String) : JsonParseable {
    STANDARD("Standard"),
    NEON("Neon"),
    NEON_LIGHT("NeonLight"),
    BASIC("Basic"),
    GEMS("Gems"),
    MINIMAL("Minimal"),
}
