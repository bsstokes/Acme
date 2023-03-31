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
            Assignment(
                driver = TestData.drivers[0],
                shipment = TestData.shipments[0],
            ),
            Assignment(
                driver = TestData.drivers[1],
                shipment = TestData.shipments[2],
            ),
            Assignment(
                driver = TestData.drivers[2],
                shipment = TestData.shipments[1],
            ),
        ).success()
    }
}

private object TestData {
    val drivers = listOf(
        Driver(name = "Everardo Welch"),    // consonants=8, vowels=5, length=14
        Driver(name = "Orval Mayert"),      // consonants=7, vowels=4, length=12
        Driver(name = "Howard Emmerich"),   // consonants=9, vowels=5, length=15
    )

    val shipments = listOf(
        Shipment(address = "215 Osinski Manors"),       // length=18
        Shipment(address = "9856 Marvin Stravenue"),    // length=21
        Shipment(address = "7127 Kathlyn Ferry"),       // length=18
    )

    /*
    Scores              | "215 Osinski Manors"  | "9856 Marvin Stravenue" | "7127 Kathlyn Ferry"
    ------------------- | --------------------- | ----------------------- | ---------------------
    "Everardo Welch"    | 5 * 1.5 * 1.5 = 11.25 | 8 * 1.0 * 1.5 = 12.00   | 5 * 1.5 * 1.5 = 11.25
    "Orval Mayert"      | 4 * 1.5 * 1.5 =  9.00 | 7 * 1.0 * 1.5 = 10.50   | 4 * 1.5 * 1.5 =  9.00
    "Howard Emmerich"   | 5 * 1.5 * 1.5 = 11.25 | 9 * 1.0 * 1.5 = 13.50   | 5 * 1.5 * 1.5 = 11.25
     */
}
