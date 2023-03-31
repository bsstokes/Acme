package com.bsstokes.acme.app.domain.usecase

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.repository.InputDataRepository
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.map

class LoadAssignmentsFromRepositoryUseCase(
    private val inputDataRepository: InputDataRepository,
) : LoadAssignmentsUseCase {

    override suspend fun loadAssignments(): SimpleResponse<List<Assignment>> {
        return inputDataRepository.getInputData().map { inputData ->

            // Naive matching
            inputData.drivers.zip(inputData.shipments).map { (driver, shipment) ->
                Assignment(
                    driver = driver,
                    shipment = shipment,
                )
            }
        }
    }
}
