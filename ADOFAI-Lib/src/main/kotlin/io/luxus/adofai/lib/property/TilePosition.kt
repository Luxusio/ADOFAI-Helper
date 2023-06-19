package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class TilePosition(override val jsonValue: String) : JsonParseable {
    THIS_TILE("ThisTile"),
    START("Start"),
    END("End"),
}
