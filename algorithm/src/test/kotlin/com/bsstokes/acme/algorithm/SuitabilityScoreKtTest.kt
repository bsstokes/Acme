package com.bsstokes.acme.algorithm

import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class SuitabilityScoreKtTest {

    @Test
    fun `suitabilityScore test`() {
        suitabilityScore(driverName = "D", shipmentAddress = "S1") shouldEqual 0.0
    }
}
