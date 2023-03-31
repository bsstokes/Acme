package com.bsstokes.acme.algorithm

import com.bsstokes.acme.algorithm.math.haveCommonPrimeFactors
import com.bsstokes.acme.algorithm.math.isEven
import com.bsstokes.acme.algorithm.words.numberOfConsonants
import com.bsstokes.acme.algorithm.words.numberOfVowels

/**
 * Calculate the suitability score (SS) according the prompt:
 *
 * * If the length of the shipment's destination street name is even,
 *   the base suitability score (SS) is the number of vowels in the driver’s name multiplied by 1.5.
 * * If the length of the shipment's destination street name is odd,
 *   the base SS is the number of consonants in the driver’s name multiplied by 1.
 * * If the length of the shipment's destination street name shares any common factors (besides 1)
 *   with the length of the driver’s name, the SS is increased by 50% above the base SS.
 */
internal fun suitabilityScore(
    driverName: String,
    shipmentAddress: String,
): Double {
    val base = if (shipmentAddress.length.isEven) {
        1.5 * driverName.numberOfVowels
    } else {
        1.0 * driverName.numberOfConsonants
    }

    val modifier = if (haveCommonPrimeFactors(shipmentAddress.length, driverName.length)) {
        1.5
    } else {
        1.0
    }

    return base * modifier
}
