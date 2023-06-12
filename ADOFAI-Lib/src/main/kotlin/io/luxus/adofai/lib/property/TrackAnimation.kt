package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class TrackAnimation(override val jsonValue: String) : JsonParseable {
    NONE("None"),
    ASSEMBLE("Assemble"),
    ASSEMBLE_FAR("Assemble_Far"),
    EXTEND("Extend"),
    GROW("Grow"),
    GROW_SPIN("Grow_Spin"),
    FADE("Fade"),
    DROP("Drop"),
    RISE("Rise"),
}
