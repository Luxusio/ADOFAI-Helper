package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Toggle

@EventType(jsonValue = "AutoPlayTiles", builderClass = AutoPlayTiles.Builder::class, single = true)
data class AutoPlayTiles(
    override val active: Boolean?,
    val enabled: Toggle,
) : Action(AutoPlayTiles::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .enabled(enabled)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var enabled: Toggle = Toggle.ENABLED
            private set

        fun enabled(enabled: Toggle) = apply { this.enabled = enabled }

        override fun build() = AutoPlayTiles(
            active,
            enabled,
        )
    }
}
