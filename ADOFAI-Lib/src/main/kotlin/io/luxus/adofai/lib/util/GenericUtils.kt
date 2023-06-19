package io.luxus.adofai.lib.util

import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.WildcardType

fun Method.getParameterTypes(at: Int): List<Class<*>> {
    val parameter = this.genericParameterTypes[at]
    if (parameter is ParameterizedType) {
        val genericTypes = parameter.actualTypeArguments.map {
            return@map when (it) {
                is Class<*> -> it
                is WildcardType -> it.upperBounds[0] as Class<*>
                else -> throw IllegalArgumentException("Unknown type: $it")
            }
        }
        return listOf(parameter.rawType as Class<*>) + genericTypes
    }

    return listOf(this.parameterTypes[at])
}
