package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class HoldMidSound(override val jsonValue: String) : JsonParseable {
    NONE("None"),
    FUSE("Fuse"),
    SING_SING("SingSing"),
}
