package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class Toggle(override val jsonValue: String) : JsonParseable {
    ENABLED("Enabled"),
    DISABLED("Disabled"),
}
