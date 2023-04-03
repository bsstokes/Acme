package com.bsstokes.acme.algorithm.hungarian

import blogspot.software_and_algorithms.stern_library.optimization.HungarianAlgorithm
import com.bsstokes.acme.domain.model.Assignment
import com.bsstokes.acme.domain.model.Driver
import com.bsstokes.acme.domain.model.Shipment

internal fun assignViaHungarianAlgorithm(
    drivers: List<Driver>,
    shipments: List<Shipment>,
    score: (driver: Driver, shipment: Shipment) -> Double,
): List<Assignment> {
    val matrix = buildMatrix(
        drivers = drivers,
        shipments = shipments,
        score = score,
    )

    val result = HungarianAlgorithm(matrix).execute()

    return result.mapIndexed { driverIndex, shipmentIndex ->
        Assignment(
            driver = drivers[driverIndex],
            shipment = shipments[shipmentIndex],
        )
    }
}

internal fun buildMatrix(
    drivers: List<Driver>,
    shipments: List<Shipment>,
    score: (driver: Driver, shipment: Shipment) -> Double,
): Array<DoubleArray> {
    return Array(drivers.size) { driverIndex ->
        DoubleArray(shipments.size) { shipmentIndex ->
            -score(drivers[driverIndex], shipments[shipmentIndex])
        }
    }
}
