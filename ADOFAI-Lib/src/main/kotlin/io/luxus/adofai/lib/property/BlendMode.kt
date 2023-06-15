package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class BlendMode(override val jsonValue: String) : JsonParseable {
    NONE("None"),
    SCREEN("Screen"),
    LINEAR_DODGE("LinearDodge"),
    OVERLAY("Overlay"),
    SOFT_LIGHT("SoftLight"),
    DIFFERENCE("Difference"),
}
