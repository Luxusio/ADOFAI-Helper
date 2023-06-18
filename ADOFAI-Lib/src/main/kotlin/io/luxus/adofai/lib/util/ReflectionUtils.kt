package io.luxus.adofai.lib.util

import java.lang.reflect.Field
import java.lang.reflect.Modifier

val Field.isStatic: Boolean
    get() = Modifier.isStatic(this.modifiers)
