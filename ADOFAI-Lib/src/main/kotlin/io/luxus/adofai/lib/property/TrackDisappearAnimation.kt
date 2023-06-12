package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class TrackDisappearAnimation(override val jsonValue: String) : JsonParseable {
    NONE("None"),
    SCATTER("Scatter"),
    SCATTER_FAR("Scatter_Far"),
    RETRACT("Retract"),
    SHRINK("Shrink"),
    SHRINK_SPIN("Shrink_Spin"),
    FADE("Fade"),
}
