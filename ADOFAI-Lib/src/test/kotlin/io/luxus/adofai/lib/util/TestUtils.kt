package io.luxus.adofai.lib.util

import java.io.File

private class Foo

fun adofaiFiles(): List<File> = resourceFile("adofai-sample").allSubFiles()

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
