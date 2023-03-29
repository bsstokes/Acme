package com.bsstokes.acme.assignments.list

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class AssignmentsListUiStateMappersKtTest {

    @Test
    fun `Assignment maps to AssignmentItem`() {
        Assignment(
            driver = Driver(name = "Elastigirl"),
            shipment = Shipment(address = "123 Fake St"),
        ).toUiState() shouldEqual AssignmentItem(
            driverName = "Elastigirl",
            shipmentAddress = "123 Fake St",
        )
    }

    @Test
    fun `list of Assignments maps to list of AssignmentItems`() {
        val assignments = listOf(
            Assignment(
                driver = Driver(name = "Elastigirl"),
                shipment = Shipment(address = "123 Fake St"),
            ),
            Assignment(
                driver = Driver(name = "Mr. Incredible"),
                shipment = Shipment(address = "123 Fake Ave"),
            ),
        )
        assignments.toUiState() shouldEqual AssignmentsListUiState(
            assignments = listOf(
                assignments[0].toUiState(),
                assignments[1].toUiState(),
            ),
        )
    }
}
