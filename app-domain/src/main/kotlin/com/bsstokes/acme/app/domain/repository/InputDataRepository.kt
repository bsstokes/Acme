package com.bsstokes.acme.app.domain.repository

import com.bsstokes.acme.app.domain.model.InputData
import com.bsstokes.acme.app.domain.response.SimpleResponse

interface InputDataRepository {
    suspend fun getInputData(): SimpleResponse<InputData>
}
