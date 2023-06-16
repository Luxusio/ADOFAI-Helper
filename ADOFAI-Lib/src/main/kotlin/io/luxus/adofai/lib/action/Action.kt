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

    companion object {
        val SINGLE_ACTIONS: Set<Class<out Action>> = setOf(
            Twirl::class.java,
            Checkpoint::class.java,
            SetHitsound::class.java,
            SetPlanetRotation::class.java,
            Pause::class.java,
            AutoPlayTiles::class.java,
            ColorTrack::class.java,
            AnimateTrack::class.java,
            PositionTrack::class.java,
            SetConditionalEvents::class.java,
            Bookmark::class.java,
            Hold::class.java,
            SetHoldSound::class.java,
            MultiPlanet::class.java,
            FreeRoam::class.java,
            Hide::class.java,
            ScaleRadius::class.java,
            ScalePlanets::class.java,
        )
    }
}

fun <T : Action> Class<T>.isSingleOnly(): Boolean =
    this in Action.SINGLE_ACTIONS
