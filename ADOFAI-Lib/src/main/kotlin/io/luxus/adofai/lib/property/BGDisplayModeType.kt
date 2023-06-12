package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class BGDisplayModeType(override val jsonValue: String) : JsonParseable {
    FIT_TO_SCREEN("FitToScreen"),
    UNSCALED("Unscaled"),
    TILED("Tiled"),
}
