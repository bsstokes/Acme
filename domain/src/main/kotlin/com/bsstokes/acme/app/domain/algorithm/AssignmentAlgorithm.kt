package com.bsstokes.acme.app.domain.algorithm

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.response.SimpleResponse

fun interface AssignmentAlgorithm {
    suspend fun makeAssignments(
        drivers: List<Driver>,
        shipments: List<Shipment>,
    ): SimpleResponse<List<Assignment>>
}
