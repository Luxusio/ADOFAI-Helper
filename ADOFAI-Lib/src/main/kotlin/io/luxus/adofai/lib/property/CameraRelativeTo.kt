package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class CameraRelativeTo(override val jsonValue: String) : JsonParseable {
    PLAYER("Player"),
    TILE("Tile"),
    GLOBAL("Global"),
    LAST_POSITION("LastPosition"),
    LAST_POSITION_NO_ROTATION("LastPositionNoRotation"),
}
