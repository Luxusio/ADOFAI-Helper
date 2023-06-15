package io.luxus.adofai.lib.action

abstract class Decoration protected constructor(
    eventType: Class<out Decoration>,
    active: Boolean?,
    val visible: Boolean?,
    val floor: Long?,
) : Action(eventType, active) {

    abstract override fun toBuilder(): Builder<*>

    abstract class Builder<T : Builder<T>> protected constructor(
    ) : Action.Builder<T>() {
        var visible: Boolean? = null
            private set
        var floor: Long? = null
            private set

        fun visible(visible: Boolean?): T = self.apply { this.visible = visible }
        fun floor(floor: Long?): T = self.apply { this.floor = floor }

        abstract override fun build(): Decoration
    }
}
