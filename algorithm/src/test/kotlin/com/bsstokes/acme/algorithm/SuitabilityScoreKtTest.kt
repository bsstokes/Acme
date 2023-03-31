package com.bsstokes.acme.algorithm

import com.bsstokes.acme.algorithm.words.numberOfConsonants
import com.bsstokes.acme.algorithm.words.numberOfVowels
import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class SuitabilityScoreKtTest {

    @Test
    fun `suitabilityScore calculates for odd length shipping address that shares factors`() {
        // "Driver" has 4 consonants and 2 vowels
        val driverName = "Driver"
        val shippingAddressWithOddLength = "123"

        suitabilityScore(
            driverName = driverName,
            shipmentAddress = shippingAddressWithOddLength,
        ) shouldEqual driverName.numberOfConsonants * 1.0 * 1.5
    }

    @Test
    fun `suitabilityScore calculates for odd length shipping address that don't share factors`() {
        // "Driver" has 4 consonants and 2 vowels
        val driverName = "Driver"
        val shippingAddressWithOddLength = "1"

        suitabilityScore(
            driverName = driverName,
            shipmentAddress = shippingAddressWithOddLength,
        ) shouldEqual driverName.numberOfConsonants * 1.0
    }

    @Test
    fun `suitabilityScore calculates for even length shipping address that shares factors`() {
        // "Driver" has 4 consonants and 2 vowels
        val driverName = "Driver"
        val shippingAddressWithEvenLength = "12"

        suitabilityScore(
            driverName = driverName,
            shipmentAddress = shippingAddressWithEvenLength,
        ) shouldEqual driverName.numberOfVowels * 1.5 * 1.5
    }

    @Test
    fun `suitabilityScore calculates for even length shipping address that don't share factors`() {
        // "Driver1" has 4 consonants and 2 vowels
        val driverName = "Driver1"
        val shippingAddressWithEvenLength = "12"

        suitabilityScore(
            driverName = driverName,
            shipmentAddress = shippingAddressWithEvenLength,
        ) shouldEqual driverName.numberOfVowels * 1.5
    }
}
