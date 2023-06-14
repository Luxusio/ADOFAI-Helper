package io.luxus.adofai.lib.util

import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import java.io.File

private class Foo

fun forAllAdofaiFiles(
    test: (file: File) -> Unit
) = forAll(
    table(
        headers("file"),
        adofaiFiles().map { row(it) }
    ),
    test
)

fun adofaiFiles(): List<File> = resourceFile("adofai-sample")
    .allSubFiles()
    .filter { it.name.endsWith(".adofai") }

fun resourceFile(path: String): File {
    return File(Foo::class.java.classLoader.getResource(path)!!.file)
}

fun File.allSubFiles(): List<File> {
    val list = mutableListOf<File>()
    this.listFiles()?.forEach {
        if (it.isDirectory) {
            list.addAll(it.allSubFiles())
        } else {
            list.add(it)
        }
    }
    return list
}
