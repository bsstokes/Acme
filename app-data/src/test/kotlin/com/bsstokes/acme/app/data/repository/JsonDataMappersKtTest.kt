package com.bsstokes.acme.app.data.repository

import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class JsonDataMappersKtTest {

    @Test
    fun `JsonData maps to InputData`() {
        JsonData(
            drivers = listOf("driver_one", "driver_two"),
            shipments = listOf("shipment_one", "shipment_two"),
        ).toInputData() shouldEqual InputData(
            drivers = listOf(
                Driver(name = "driver_one"),
                Driver(name = "driver_two"),
            ),
            shipments = listOf(
                Shipment(address = "shipment_one"),
                Shipment(address = "shipment_two"),
            ),
        )
    }
}
