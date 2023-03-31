package com.bsstokes.acme.app.domain.usecase

import com.bsstokes.acme.app.domain.algorithm.AssignmentAlgorithm
import com.bsstokes.acme.app.domain.algorithm.NaiveAssignmentAlgorithm
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.repository.InputDataRepository
import com.bsstokes.acme.app.domain.response.ErrorResponse
import com.bsstokes.acme.app.domain.response.success
import com.bsstokes.acme.test.shouldEqual
import io.mockk.coVerify
import io.mockk.spyk
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
            assignmentAlgorithm = NaiveAssignmentAlgorithm(),
        )

        useCase.loadAssignments() shouldEqual ErrorResponse
    }

    @Test
    fun `loadAssignments returns assignments from algorithm`() = runTest {
        val drivers = listOf(Driver("D_1"), Driver("D_2"))
        val shipments = listOf(Shipment("S_1"), Shipment("S_2"))
        val inputData = InputData(drivers = drivers, shipments = shipments)

        val assignmentAlgorithm: AssignmentAlgorithm = spyk(NaiveAssignmentAlgorithm())

        val useCase = LoadAssignmentsFromRepositoryUseCase(
            inputDataRepository = object : InputDataRepository {
                override suspend fun getInputData() = inputData.success()
            },
            assignmentAlgorithm = assignmentAlgorithm,
        )

        val assignments = useCase.loadAssignments()
        coVerify { assignmentAlgorithm.makeAssignments(drivers, shipments) }
        assignments shouldEqual assignmentAlgorithm.makeAssignments(drivers, shipments)
    }

    @Test
    fun `loadAssignments returns error if algorithm returns error`() = runTest {
        val inputData = InputData(drivers = emptyList(), shipments = emptyList())

        val useCase = LoadAssignmentsFromRepositoryUseCase(
            inputDataRepository = object : InputDataRepository {
                override suspend fun getInputData() = inputData.success()
            },
            assignmentAlgorithm = { _, _ -> ErrorResponse },
        )

        useCase.loadAssignments() shouldEqual ErrorResponse
    }
}
