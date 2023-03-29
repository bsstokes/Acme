package com.bsstokes.acme.assignments.list

data class AssignmentsListUiState(
    val assignments: List<AssignmentItem>,
)

data class AssignmentItem(
    val driverName: String,
    val shipmentAddress: String,
)
