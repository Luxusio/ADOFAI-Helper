package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class Plane(override val jsonValue: String) : JsonParseable {
    BACKGROUND("Background"),
    FOREGROUND("Foreground"),
}
