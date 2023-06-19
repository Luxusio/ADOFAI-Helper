package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class EasePartBehavior(override val jsonValue: String) : JsonParseable {
    MIRROR("Mirror"),
    REPEAT("Repeat"),
}
