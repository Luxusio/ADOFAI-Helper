package io.luxus.adofai.lib

data class TileAngle(
    val midSpin: Boolean,
    private val _angle: Double,
) {
    val angle: Double
        get() = if (midSpin) {
            throw UnsupportedOperationException("angle is midSpin")
        } else {
            _angle
        }

    companion object {
        val MID_SPIN = TileAngle(true, 0.0)
        val _0 = create(0.0)

        fun create(angle: Double) = TileAngle(false, angle)
    }
}
