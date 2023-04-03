package com.bsstokes.acme.algorithm.hungarian

import com.bsstokes.acme.domain.model.Driver
import com.bsstokes.acme.domain.model.Shipment
import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class HungarianAlgorithmWrapperKtTest {

    @Test
    fun `build correct matrix with negated scores`() {
        val drivers = listOf(Driver("D"), Driver("DD"))
        val shipments = listOf(Shipment("SSS"), Shipment("SSSS"))
        val score = { driver: Driver, shipment: Shipment ->
            (driver.name.length * shipment.address.length).toDouble()
        }

        buildMatrix(
            drivers = drivers,
            shipments = shipments,
            score = score,
        ) shouldEqual arrayOf(
            doubleArrayOf(-3.0, -4.0),
            doubleArrayOf(-6.0, -8.0),
        )
    }
}
