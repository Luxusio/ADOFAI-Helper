package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class FailHitboxType(override val jsonValue: String) : JsonParseable {
    BOX("Box"),
    CIRCLE("Circle"),
    CAPSULE("Capsule"),
}
