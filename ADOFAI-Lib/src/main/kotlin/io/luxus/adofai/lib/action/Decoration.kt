package io.luxus.adofai.lib.action

abstract class Decoration protected constructor(
    eventType: Class<out Decoration>,
    open val visible: Boolean?,
    open val floor: Long?,
) : Action(eventType, null) {
    override val active: Boolean? = null

    abstract override fun toBuilder(): Builder<*>

    abstract class Builder<T : Builder<T>> protected constructor(
    ) : Action.Builder<T>() {
        private var _visible: Boolean? = null
        val visible: Boolean? get() = _visible ?: active

        var floor: Long? = null
            private set

        fun visible(visible: Boolean?): T = self.apply { this._visible = visible }
        fun floor(floor: Long?): T = self.apply { this.floor = floor }

        abstract override fun build(): Decoration
    }
}
