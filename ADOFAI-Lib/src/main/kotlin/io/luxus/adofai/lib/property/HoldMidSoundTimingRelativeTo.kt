package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class HoldMidSoundTimingRelativeTo(override val jsonValue: String) : JsonParseable {
    END("End"),
    START("Start"),
}
