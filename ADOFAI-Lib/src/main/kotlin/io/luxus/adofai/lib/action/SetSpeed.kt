package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.SpeedType
import io.luxus.adofai.lib.util.mulOf

@EventType(jsonValue = "SetSpeed", builderClass = SetSpeed.Builder::class, single = false)
class SetSpeed private constructor(
    active: Boolean?,
    val speedType: SpeedType,
    val beatsPerMinute: Double,
    val bpmMultiplier: Double,
    val angleOffset: Double,
) : Action(SetSpeed::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .speedType(speedType)
        .beatsPerMinute(beatsPerMinute)
        .bpmMultiplier(bpmMultiplier)
        .angleOffset(angleOffset)

    companion object {
        fun calculateBpm(
            prevTileBpm: Double,
            travelAngle: Double,
            actions: List<SetSpeed>
        ): CalculateBpmResult {

            var prevAngleOffset = 0.0
            var prevBpm = prevTileBpm

            val bpmAndRatios = mutableListOf<Pair<Double, Double>>()

            actions.groupBy { it.angleOffset }
                .values
                .map { it.last() } // todo : check what happen if it has same angleOffset
                .sortedBy { it.angleOffset }
                .forEach {

                    val currAngleOffset = it.angleOffset
                    val currBpm = when (it.speedType) {
                        SpeedType.BPM -> it.beatsPerMinute
                        SpeedType.MULTIPLIER -> prevBpm * it.bpmMultiplier
                    }

                    val prevBpmRatio = (currAngleOffset - prevAngleOffset) / travelAngle

                    bpmAndRatios.add(Pair(prevBpm, prevBpmRatio))

                    prevAngleOffset = currAngleOffset
                    prevBpm = currBpm
                }

            bpmAndRatios.add(Pair(prevBpm, (travelAngle - prevAngleOffset) / travelAngle))

            val multiply = bpmAndRatios.mulOf { it.first }
            val currTileBpm = multiply / bpmAndRatios.sumOf { multiply / it.first * it.second }
            val nextTileBpm = bpmAndRatios.last().first

            return CalculateBpmResult(currTileBpm, nextTileBpm)
        }
    }

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var speedType: SpeedType = SpeedType.BPM
            private set
        var beatsPerMinute: Double = 100.0
            private set
        var bpmMultiplier: Double = 1.0
            private set
        var angleOffset: Double = 0.0
            private set


        fun speedType(speedType: SpeedType) = apply { this.speedType = speedType }

        fun beatsPerMinute(beatsPerMinute: Double) = apply { this.beatsPerMinute = beatsPerMinute }

        fun bpmMultiplier(bpmMultiplier: Double) = apply { this.bpmMultiplier = bpmMultiplier }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }

        override fun build() = SetSpeed(
            active,
            speedType,
            beatsPerMinute,
            bpmMultiplier,
            angleOffset,
        )
    }
}

class CalculateBpmResult(
    val currTileBpm: Double,
    val nextTileBpm: Double
)
