package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class DefaultBGShapeType(override val jsonValue: String) : JsonParseable {
    DEFAULT("Default"),             // Default
    SINGLE_COLOR("SingleColor"),    // Solid Color
    DISABLED("Disabled"),           // None
}
