package io.luxus.adofai.lib.util


val HEX_DIGITS = "0123456789abcdef".toCharArray()

/**
 * throws if value is not in range
 * @return this
 * @throws IllegalArgumentException if value is not in range
 */
fun Int.requireRange(min: Int, max: Int): Int {
    if (this < min || this > max) {
        throw IllegalArgumentException("$this is not in range $min..$max")
    }
    return this
}

fun Long.requireMin(min: Long): Long {
    if (this < min) {
        throw IllegalArgumentException("$this is smaller than $min")
    }
    return this
}
