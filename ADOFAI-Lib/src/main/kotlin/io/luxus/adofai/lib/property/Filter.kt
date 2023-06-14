package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class Filter(override val jsonValue: String) : JsonParseable {
    GRAYSCALE("Grayscale"),
    SEPIA("Sepia"),
    INVERT("Invert"),
    VHS("VHS"),
    EIGHTIES_TV("EightiesTV"),
    FIFTIES_TV("FiftiesTV"),
    ARCADE("Arcade"),
    LED("LED"),
    RAIN("Rain"),
    BLIZZARD("Blizzard"),
    PIXEL_SNOW("PixelSnow"),
    COMPRESSION("Compression"),
    GLITCH("Glitch"),
    PIXELATE("Pixelate"),
    WAVES("Waves"),
    STATIC("Static"),
    GRAIN("Grain"),
    MOTION_BLUR("MotionBlur"),
    FISHEYE("Fisheye"),
    ABERRATION("Aberration"),
    DRAWING("Drawing"),
    NEON("Neon"),
    HANDHELD("Handheld"),
    NIGHT_VISION("NightVision"),
    FUNK("Funk"),
    TUNNEL("Tunnel"),
    WEIRD_3D("Weird3D"),
}
