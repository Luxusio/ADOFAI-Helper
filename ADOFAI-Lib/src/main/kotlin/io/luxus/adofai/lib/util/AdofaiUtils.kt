package io.luxus.adofai.lib.util

import io.luxus.adofai.lib.TileAngle

data class CalculateAngleResult(
    val currAbsoluteAngle: Double,
    val currTravelAngle: Double,
)

fun calculateAngle(
    prevAbsoluteAngle: Double,
    currAngle: TileAngle,
    nextAngle: TileAngle,
    planetAngle: Double,
    twirl: Boolean,
): CalculateAngleResult {
    var currAbsoluteAngle = if (currAngle.midSpin) {
        prevAbsoluteAngle
    } else {
        currAngle.angle
    }
    var currTravelAngle: Double

    if (nextAngle.midSpin) {
        currTravelAngle = 0.0
        if (currAngle.midSpin) {
            currAbsoluteAngle += planetAngle
            currAbsoluteAngle = currAbsoluteAngle.generalizedAngle()
        }
    } else {
        currTravelAngle = currAbsoluteAngle - nextAngle.angle
        if (twirl) {
            currTravelAngle = -currTravelAngle
        }
        if (!currAngle.midSpin) {
            currTravelAngle += planetAngle
        }
        currTravelAngle = currTravelAngle.generalizedAngle()
        currTravelAngle = if (currTravelAngle == 0.0) 360.0 else currTravelAngle
    }
    return CalculateAngleResult(currAbsoluteAngle, currTravelAngle)
}

fun calculateNextAbsoluteAngle(
    absoluteAngle: Double,
    travelAngle: Double,
    planetAngle: Double,
    twirl: Boolean,
): Double = (if (travelAngle == 0.0) {
    if (twirl) {
        absoluteAngle + planetAngle
    } else {
        absoluteAngle - planetAngle
    }
} else {
    if (twirl) {
        absoluteAngle + travelAngle - planetAngle
    } else {
        absoluteAngle - travelAngle + planetAngle
    }
}).generalizedAngle()

fun Long.planetAngle() = 360.0 / this

fun Double.generalizedAngle() = ((this % 360) + 360) % 360
