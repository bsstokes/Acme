package com.bsstokes.acme.domain.algorithm

import com.bsstokes.acme.domain.model.Assignment
import com.bsstokes.acme.domain.model.Driver
import com.bsstokes.acme.domain.model.Shipment
import com.bsstokes.acme.domain.response.SimpleResponse

fun interface AssignmentAlgorithm {
    suspend fun makeAssignments(
        drivers: List<Driver>,
        shipments: List<Shipment>,
    ): SimpleResponse<List<Assignment>>
}
