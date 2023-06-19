package io.luxus.adofai.lib.util

import java.io.Reader

fun Reader.readChar(): Char? = this.read().let {
    if (it == -1) {
        return null
    } else {
        it.toChar()
    }
}

