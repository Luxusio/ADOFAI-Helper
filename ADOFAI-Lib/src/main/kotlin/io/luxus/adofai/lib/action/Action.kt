package io.luxus.adofai.lib.action

abstract class Action protected constructor(
    val eventType: Class<out Action>,
    val active: Boolean?,
) {
    abstract fun toBuilder(): Builder<*>

    abstract class Builder<T : Builder<T>> protected constructor() {
        protected abstract val self: T

        var active: Boolean? = null
            private set

        fun active(active: Boolean?): T = self.apply { this.active = active }

        abstract fun build(): Action
    }
}
