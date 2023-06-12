package io.luxus.adofai.lib.util

import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

fun Method.getParameterTypes(at: Int): List<Class<*>> {
    val parameter = this.genericParameterTypes[at]
    if (parameter is ParameterizedType) {
        val genericTypes = parameter.actualTypeArguments.map { it as Class<*> }
        return listOf(parameter.rawType as Class<*>) + genericTypes
    }

    return listOf(this.parameterTypes[at])
}
