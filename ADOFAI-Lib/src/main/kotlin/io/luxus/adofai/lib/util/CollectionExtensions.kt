package io.luxus.adofai.lib.util

inline fun <T> Iterable<T>.mulOf(selector: (T) -> Double): Double {
    var mul = 1.0
    for (element in this) {
        mul *= selector(element)
    }
    return mul
}
