package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Toggle

class AutoPlayTiles(
    active: Boolean?,
    val enabled: Toggle,
    val safetyTiles: Toggle,
) : Action(AutoPlayTiles::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .enabled(enabled)
        .safetyTiles(safetyTiles)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var enabled: Toggle = Toggle.ENABLED
            private set
        var safetyTiles: Toggle = Toggle.DISABLED
            private set

        fun enabled(enabled: Toggle) = apply { this.enabled = enabled }
        fun safetyTiles(safetyTiles: Toggle) = apply { this.safetyTiles = safetyTiles }

        override fun build() = AutoPlayTiles(
            active,
            enabled,
            safetyTiles
        )
    }
}
