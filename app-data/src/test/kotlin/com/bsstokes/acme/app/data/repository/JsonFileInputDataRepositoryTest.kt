package com.bsstokes.acme.app.data.repository

import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.response.success
import com.bsstokes.acme.test.shouldEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class JsonFileInputDataRepositoryTest {

    @Test
    fun `reads driver name from JsonFileReader`() = runTest {
        val repository = JsonFileInputDataRepository(
            jsonFileReader = { testData.byteInputStream() },
            ioDispatcher = UnconfinedTestDispatcher(testScheduler),
        )

        repository.getInputData() shouldEqual InputData(
            drivers = listOf(Driver(testData)),
            shipments = emptyList(),
        ).success()
    }

    private val testData = """
        {
          "drivers": [
            "Driver1",
            "Driver2"
          ],
          "shipments": [
            "Shipment1",
            "Shipment2"
          ]
        }""".trimIndent()
}
