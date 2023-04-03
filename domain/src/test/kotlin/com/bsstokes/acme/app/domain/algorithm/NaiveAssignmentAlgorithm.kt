package com.bsstokes.acme.app.domain.algorithm

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.success

class NaiveAssignmentAlgorithm : AssignmentAlgorithm {
    override suspend fun makeAssignments(
        drivers: List<Driver>,
        shipments: List<Shipment>
    ): SimpleResponse<List<Assignment>> {
        return drivers.zip(shipments).map { (driver, shipment) ->
            Assignment(driver, shipment)
        }.success()
    }
}
