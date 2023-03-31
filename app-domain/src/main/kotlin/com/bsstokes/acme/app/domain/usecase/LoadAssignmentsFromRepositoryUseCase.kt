package com.bsstokes.acme.app.domain.usecase

import com.bsstokes.acme.app.domain.algorithm.AssignmentAlgorithm
import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.repository.InputDataRepository
import com.bsstokes.acme.app.domain.response.ErrorResponse
import com.bsstokes.acme.app.domain.response.SimpleResponse

class LoadAssignmentsFromRepositoryUseCase(
    private val inputDataRepository: InputDataRepository,
    private val assignmentAlgorithm: AssignmentAlgorithm,
) : LoadAssignmentsUseCase {

    override suspend fun loadAssignments(): SimpleResponse<List<Assignment>> {
        val inputDataResponse = inputDataRepository.getInputData()
        val inputData = inputDataResponse.getOrNull() ?: return ErrorResponse

        return assignmentAlgorithm.makeAssignments(
            drivers = inputData.drivers,
            shipments = inputData.shipments,
        )
    }
}
