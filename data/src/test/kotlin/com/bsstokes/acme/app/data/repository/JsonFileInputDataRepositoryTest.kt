package com.bsstokes.acme.app.data.repository

import com.bsstokes.acme.domain.model.Driver
import com.bsstokes.acme.domain.model.InputData
import com.bsstokes.acme.domain.model.Shipment
import com.bsstokes.acme.domain.response.ErrorResponse
import com.bsstokes.acme.domain.response.success
import com.bsstokes.acme.test.shouldEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class JsonFileInputDataRepositoryTest {

    @Test
    fun `reads driver and shipment data from JsonFileReader`() = runTest {
        val repository = JsonFileInputDataRepository(
            jsonFileReader = { testData.byteInputStream() },
            ioDispatcher = UnconfinedTestDispatcher(testScheduler),
        )

        repository.getInputData() shouldEqual InputData(
            drivers = listOf(Driver("Driver1"), Driver("Driver2")),
            shipments = listOf(Shipment("Shipment1"), Shipment("Shipment2")),
        ).success()
    }

    @Test
    fun `returns error when reading data fails`() = runTest {
        val repository = JsonFileInputDataRepository(
            jsonFileReader = { error("Something went wrong!") },
            ioDispatcher = UnconfinedTestDispatcher(testScheduler),
        )

        repository.getInputData() shouldEqual ErrorResponse
    }

    @Test
    fun `returns error when deserializing data fails`() = runTest {
        val repository = JsonFileInputDataRepository(
            jsonFileReader = { "can't decode this".byteInputStream() },
            ioDispatcher = UnconfinedTestDispatcher(testScheduler),
        )

        repository.getInputData() shouldEqual ErrorResponse
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
