package com.bsstokes.acme.app.domain.usecase

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.repository.InputDataRepository
import com.bsstokes.acme.app.domain.response.ErrorResponse
import com.bsstokes.acme.app.domain.response.success
import com.bsstokes.acme.test.shouldEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadAssignmentsFromRepositoryUseCaseTest {

    @Test
    fun `loadAssignments returns error when repository does`() = runTest {
        val useCase = LoadAssignmentsFromRepositoryUseCase(
            inputDataRepository = object : InputDataRepository {
                override suspend fun getInputData() = ErrorResponse
            },
        )

        useCase.loadAssignments() shouldEqual ErrorResponse
    }

    @Test
    fun `loadAssignments returns naive matching of drivers and shipments`() = runTest {
        val drivers = listOf(Driver("D#1"), Driver("D#2"))
        val shipments = listOf(Shipment("S#1"), Shipment("S#2"))
        val inputData = InputData(drivers = drivers, shipments = shipments)

        val useCase = LoadAssignmentsFromRepositoryUseCase(
            inputDataRepository = object : InputDataRepository {
                override suspend fun getInputData() = inputData.success()
            },
        )

        useCase.loadAssignments() shouldEqual listOf(
            Assignment(
                driver = drivers[0],
                shipment = shipments[0],
            ),
            Assignment(
                driver = drivers[1],
                shipment = shipments[1],
            ),
        ).success()
    }
}
