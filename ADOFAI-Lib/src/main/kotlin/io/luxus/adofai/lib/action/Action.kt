package io.luxus.adofai.lib.action

import kotlin.reflect.KClass

abstract class Action protected constructor(
    val eventType: Class<out Action>,
    open val active: Boolean?,
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
        val CLASSES: Map<Class<out Action>, EventType> = listOf(
            AddDecoration::class.java,
            AddText::class.java,
            AnimateTrack::class.java,
            AutoPlayTiles::class.java,
            Bloom::class.java,
            Bookmark::class.java,
            ChangeTrack::class.java,
            Checkpoint::class.java,
            ColorTrack::class.java,
            CustomBackground::class.java,
            EditorComment::class.java,
            Flash::class.java,
            FreeRoam::class.java,
            FreeRoamRemove::class.java,
            FreeRoamTwirl::class.java,
            HallOfMirrors::class.java,
            Hide::class.java,
            Hold::class.java,
            MoveCamera::class.java,
            MoveDecorations::class.java,
            MoveTrack::class.java,
            MultiPlanet::class.java,
            Pause::class.java,
            PlaySound::class.java,
            PositionTrack::class.java,
            RecolorTrack::class.java,
            RepeatEvents::class.java,
            ScaleMargin::class.java,
            ScalePlanets::class.java,
            ScaleRadius::class.java,
            ScreenScroll::class.java,
            ScreenTile::class.java,
            SetConditionalEvents::class.java,
            SetFilter::class.java,
            SetHitsound::class.java,
            SetHoldSound::class.java,
            SetPlanetRotation::class.java,
            SetSpeed::class.java,
            SetText::class.java,
            ShakeScreen::class.java,
            Twirl::class.java,
        ).associateWith { it.getAnnotation(EventType::class.java) }

        val SINGLE_ACTIONS: Set<Class<out Action>> = CLASSES.filter { it.value.single }.map { it.key }.toSet()
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EventType(
    val jsonValue: String,
    val builderClass: KClass<out Action.Builder<*>>,
    val single: Boolean,
)

fun <T : Action> Class<T>.isSingleOnly(): Boolean =
    this in Action.SINGLE_ACTIONS
