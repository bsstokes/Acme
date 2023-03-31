package com.bsstokes.acme.algorithm

import com.bsstokes.acme.algorithm.hungarian.assignViaHungarianAlgorithm
import com.bsstokes.acme.app.domain.algorithm.AssignmentAlgorithm
import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HungarianAssignmentAlgorithm(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : AssignmentAlgorithm {

    override suspend fun makeAssignments(
        drivers: List<Driver>,
        shipments: List<Shipment>
    ): SimpleResponse<List<Assignment>> {
        return withContext(dispatcher) {
            val assignments = assignViaHungarianAlgorithm(
                drivers = drivers,
                shipments = shipments,
                score = { driver, shipment ->
                    suitabilityScore(
                        driverName = driver.name,
                        shipmentAddress = shipment.address,
                    )
                },
            )
            assignments.success()
        }
    }
}
