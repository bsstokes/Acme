package com.bsstokes.acme.algorithm

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.response.success
import com.bsstokes.acme.test.shouldEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HungarianAssignmentAlgorithmTest {

    @Test
    fun `makes assignments`() = runTest {
        val algorithm = HungarianAssignmentAlgorithm(UnconfinedTestDispatcher(testScheduler))
        algorithm.makeAssignments(TestData.drivers, TestData.shipments) shouldEqual listOf(
            Assignment(driver = TestData.drivers[0], shipment = TestData.shipments[0])
        ).success()
    }
}

private object TestData {
    val drivers = listOf(
        "Everardo Welch",
        "Orval Mayert",
        "Howard Emmerich",
    ).map { Driver(name = it) }

    val shipments = listOf(
        "215 Osinski Manors",
        "9856 Marvin Stravenue",
        "7127 Kathlyn Ferry",
    ).map { Shipment(address = it) }
}
