package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class MaskingType(override val jsonValue: String) : JsonParseable {
    NONE("None"),
    MASK("Mask"),
    VISIBLE_INSIDE_MASK("VisibleInsideMask"),
    VISIBLE_OUTSIDE_MASK("VisibleOutsideMask"),
}
