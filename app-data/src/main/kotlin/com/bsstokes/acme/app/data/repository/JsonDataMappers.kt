package com.bsstokes.acme.app.data.repository

import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.model.Shipment

internal fun JsonData.toInputData() = InputData(
    drivers = drivers.map { driverName ->
        Driver(name = driverName)
    },
    shipments = shipments.map { shipmentAddress ->
        Shipment(address = shipmentAddress)
    },
)