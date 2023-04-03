package com.bsstokes.acme.data.repository

import com.bsstokes.acme.domain.model.Driver
import com.bsstokes.acme.domain.model.InputData
import com.bsstokes.acme.domain.model.Shipment

internal fun JsonData.toInputData() = InputData(
    drivers = drivers.map { driverName ->
        Driver(name = driverName)
    },
    shipments = shipments.map { shipmentAddress ->
        Shipment(address = shipmentAddress)
    },
)
