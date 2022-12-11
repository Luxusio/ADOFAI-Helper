package io.luxus.lib.adofai.util

import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

class StringReader(inputStream: InputStream, bufferSize: Int = 1024): Iterator<Char> {

    private val reader: Reader = InputStreamReader(inputStream, Charsets.UTF_8)
    private val buffer = CharArray(bufferSize)
    private var numRead: Int = reader.read(buffer, 0, buffer.size)
    private var charIdx: Int = 0

    fun readString(): String {
        val out = java.lang.StringBuilder()
        out.append(buffer, 0, numRead)

        while (reader.read(buffer, 0, buffer.size).also { numRead = it } > 0) {
            out.append(buffer, 0, numRead)
        }

        return out.toString()
    }

    override fun hasNext(): Boolean {
        return !(numRead < buffer.size && numRead == charIdx)
    }

    override fun next(): Char {
        if (charIdx == numRead) {
            charIdx = 0
            numRead = reader.read(buffer, 0, buffer.size)
        }

        return buffer[charIdx++]
    }


}