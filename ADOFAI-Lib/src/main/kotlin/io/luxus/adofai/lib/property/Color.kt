package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.util.HEX_DIGITS
import io.luxus.adofai.lib.util.requireRange

class Color private constructor(
    val r: Int,
    val g: Int,
    val b: Int,
) {
    /**
     * hex-encoded rgb value
     */
    val jsonValue: String =
        "${HEX_DIGITS[r shr 4]}${HEX_DIGITS[r and 0xF]}${HEX_DIGITS[g shr 4]}${HEX_DIGITS[g and 0xF]}${HEX_DIGITS[b shr 4]}${HEX_DIGITS[b and 0xF]}"

    fun toBuilder() = Builder().rgb(r, g, b)

    open class Builder {
        private var r: Int = 0
        private var g: Int = 0
        private var b: Int = 0

        fun rgb(value: String) = apply {
            require(value.length >= 6) { "value's length should not smaller than 6 (given=$value)" }
            rgb(
                value.substring(0, 2).toInt(16),
                value.substring(2, 4).toInt(16),
                value.substring(4, 6).toInt(16)
            )
        }

        fun rgb(r: Int, g: Int, b: Int) = apply {
            r(r)
            g(g)
            b(b)
        }

        fun r(r: Int) = apply { this.r = r.requireRange(0, 255) }
        fun g(g: Int) = apply { this.g = g.requireRange(0, 255) }
        fun b(b: Int) = apply { this.b = b.requireRange(0, 255) }
        open fun build(): Color = Color(r, g, b)
    }
}
