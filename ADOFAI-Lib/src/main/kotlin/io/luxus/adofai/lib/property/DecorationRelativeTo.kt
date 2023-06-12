package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class DecorationRelativeTo(override val jsonValue: String) : JsonParseable {
    TILE("Tile"),
    GLOBAL("Global"),
    RED_PLANET("RedPlanet"),
    BLUE_PLANET("BluePlanet"),
    CAMERA("Camera"),
    CAMERA_ASPECT("CameraAspect"),
}
