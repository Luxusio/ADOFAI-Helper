package io.luxus.adofai.lib

import io.luxus.adofai.lib.action.*
import io.luxus.adofai.lib.util.calculateAngle
import io.luxus.adofai.lib.util.planetAngle

class TileMeta private constructor(
    val floor: Int,
    val bpm: Double,
    val nextTileBpm: Double,
    val absoluteAngle: Double,
    val travelAngle: Double,
    val twirl: Boolean,
    val additionalSleepAngle: Double,
    val hold: Boolean,
    val planets: Long,
) {


    companion object {
        fun createFirst(
            actionMap: ActionMap,
            customLevelSetting: CustomLevelSetting,
            nextAngle: TileAngle,
        ): TileMeta = Builder(actionMap, customLevelSetting, nextAngle).build()

        fun createAfterFirst(
            actionMap: ActionMap,
            prevTileMeta: TileMeta,
            currAngle: TileAngle,
            nextAngle: TileAngle,
        ): TileMeta = Builder(actionMap, prevTileMeta, currAngle, nextAngle).build()
    }

    private class Builder {

        private val floor: Int
        private var bpm: Double
        private var nextTileBpm: Double
        private var absoluteAngle: Double
        private var travelAngle = 0.0
        private var twirl: Boolean
        private var additionalSleepAngle = 0.0
        private var hold = false
        private var planets: Long

        // first
        constructor(
            actionMap: ActionMap,
            customLevelSetting: CustomLevelSetting,
            nextAngle: TileAngle,
        ) {
            floor = 0
            bpm = customLevelSetting.bpm
            nextTileBpm = bpm
            absoluteAngle = 0.0
            travelAngle = 360.0
            twirl = false
            planets = 2L
            update(actionMap, 0.0, TileAngle._0, nextAngle)
        }

        // after first
        constructor(
            actionMap: ActionMap,
            prevTileMeta: TileMeta,
            currAngle: TileAngle,
            nextAngle: TileAngle,
        ) {
            floor = prevTileMeta.floor + 1
            bpm = prevTileMeta.nextTileBpm
            nextTileBpm = bpm
            absoluteAngle = prevTileMeta.absoluteAngle
            twirl = prevTileMeta.twirl
            planets = prevTileMeta.planets
            update(actionMap, prevTileMeta.absoluteAngle, currAngle, nextAngle)
        }

        private fun update(
            actionMap: ActionMap,
            staticAngle: Double,
            currAngle: TileAngle,
            nextAngle: TileAngle,
        ) {
            actionMap.getFirstOrNull(Twirl::class.java)?.let {
                twirl = !twirl
            }

            // planets
            actionMap.getFirstOrNull(MultiPlanet::class.java)?.let {
                planets = it.planets.count
            }

            val result = calculateAngle(
                staticAngle, currAngle, nextAngle, planets.planetAngle(), twirl
            )

            absoluteAngle = result.currAbsoluteAngle
            travelAngle = result.currTravelAngle

            actionMap[SetSpeed::class.java].let {
                val bpmResult = SetSpeed.calculateBpm(bpm, travelAngle, it)
                bpm = bpmResult.currTileBpm
                nextTileBpm = bpmResult.nextTileBpm
            }

            actionMap.getFirstOrNull(Hold::class.java)?.let {
                hold = true
                additionalSleepAngle = 360.0 * it.duration
            }

            actionMap.getFirstOrNull(FreeRoam::class.java)?.let {
                additionalSleepAngle = 180.0 * it.duration
            }

            actionMap.getFirstOrNull(Pause::class.java)?.let {
                additionalSleepAngle = 180.0 * it.duration
            }
        }

        fun build(): TileMeta {
            return TileMeta(
                floor, bpm, nextTileBpm, absoluteAngle, travelAngle, twirl, additionalSleepAngle, hold, planets,
            )
        }
    }

}
