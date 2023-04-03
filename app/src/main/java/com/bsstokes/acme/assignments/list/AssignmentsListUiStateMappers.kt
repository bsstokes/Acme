package com.bsstokes.acme.assignments.list

import com.bsstokes.acme.domain.model.Assignment

fun Assignment.toUiState() = AssignmentItem(
    driverName = driver.name,
    shipmentAddress = shipment.address,
)

fun List<Assignment>.toUiState() = AssignmentsListUiState(
    assignments = map(Assignment::toUiState)
)
