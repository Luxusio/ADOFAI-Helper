package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.util.HEX_DIGITS
import io.luxus.adofai.lib.util.requireRange

class AlphaColor private constructor(
    val r: Int,
    val g: Int,
    val b: Int,
    val a: Int?,
) {

    val notNullA: Int = a ?: 255

    val jsonValue: String =
        if (a == null) {
            "${HEX_DIGITS[r shr 4]}${HEX_DIGITS[r and 0xF]}${HEX_DIGITS[g shr 4]}${HEX_DIGITS[g and 0xF]}${HEX_DIGITS[b shr 4]}${HEX_DIGITS[b and 0xF]}"
        } else {
            "${HEX_DIGITS[r shr 4]}${HEX_DIGITS[r and 0xF]}${HEX_DIGITS[g shr 4]}${HEX_DIGITS[g and 0xF]}${HEX_DIGITS[b shr 4]}${HEX_DIGITS[b and 0xF]}${HEX_DIGITS[a shr 4]}${HEX_DIGITS[a and 0xF]}"
        }

    fun toBuilder() = Builder().rgba(r, g, b, a)

    companion object {
        val WHITE = AlphaColor(255, 255, 255, null)
    }

    open class Builder {
        private var r: Int = 0
        private var g: Int = 0
        private var b: Int = 0
        private var a: Int? = null

        fun rgba(value: String) = apply {
            require(value.length >= 6) { "value's length should not smaller than 6 (given=$value)" }
            rgba(
                value.substring(0, 2).toInt(16),
                value.substring(2, 4).toInt(16),
                value.substring(4, 6).toInt(16),
                if (value.length >= 8) {
                    value.substring(6, 8).toInt(16)
                } else {
                    null
                }
            )
        }

        fun rgba(r: Int, g: Int, b: Int, a: Int?) = apply {
            r(r)
            g(g)
            b(b)
            a(a)
        }

        fun r(r: Int) = apply { this.r = r.requireRange(0, 255) }
        fun g(g: Int) = apply { this.g = g.requireRange(0, 255) }
        fun b(b: Int) = apply { this.b = b.requireRange(0, 255) }
        fun a(a: Int?) = apply { this.a = a?.requireRange(0, 255) }
        open fun build() = AlphaColor(r, g, b, a)
    }
}
