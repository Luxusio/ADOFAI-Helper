package io.luxus.adofai.lib.util

import java.lang.reflect.Field
import java.lang.reflect.Modifier
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure

val Field.isStatic: Boolean
    get() = Modifier.isStatic(this.modifiers)

fun KType.toClassList(): List<Class<*>> {
    val mainClass = this.jvmErasure.java
    val argumentClasses = this.arguments.mapNotNull { it.type?.jvmErasure?.java }

    return listOf(mainClass) + argumentClasses
}
