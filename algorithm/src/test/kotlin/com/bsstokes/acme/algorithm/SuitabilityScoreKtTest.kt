package com.bsstokes.acme.algorithm

import com.bsstokes.acme.algorithm.words.numberOfConsonants
import com.bsstokes.acme.algorithm.words.numberOfVowels
import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class SuitabilityScoreKtTest {

    @Test
    fun `suitabilityScore should match specs for odd length shipping address`() {
        // "Driver" has 4 consonants and 2 vowels
        val driverName = "Driver"
        suitabilityScore(
            driverName = driverName,
            shipmentAddress = "1",
        ) shouldEqual driverName.numberOfConsonants * 1.0
    }

    @Test
    fun `suitabilityScore should match specs for even length shipping address`() {
        // "Driver" has 4 consonants and 2 vowels
        val driverName = "Driver"
        suitabilityScore(
            driverName = driverName,
            shipmentAddress = "12",
        ) shouldEqual driverName.numberOfVowels * 1.5
    }
}
