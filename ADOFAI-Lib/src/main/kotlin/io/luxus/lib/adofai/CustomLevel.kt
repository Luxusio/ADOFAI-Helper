package io.luxus.lib.adofai

import java.io.InputStream

class CustomLevel {

    companion object {

        fun read(inputStream: InputStream): CustomLevel {
            return CustomLevel()
        }

    }

}