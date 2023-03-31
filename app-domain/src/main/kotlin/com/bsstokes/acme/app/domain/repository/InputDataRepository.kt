package com.bsstokes.acme.app.domain.repository

import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.success
import kotlinx.coroutines.delay

interface InputDataRepository {
    suspend fun getInputData(): SimpleResponse<InputData>
}

class FakeInputDataRepository : InputDataRepository {
    override suspend fun getInputData(): SimpleResponse<InputData> {
        delay(2_000)
        return InputData(
            drivers = FakeData.drivers.map { Driver(name = it) },
            shipments = FakeData.shipments.map { Shipment(address = it) },
        ).success()
    }
}
