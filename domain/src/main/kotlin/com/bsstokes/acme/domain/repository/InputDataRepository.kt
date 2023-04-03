package com.bsstokes.acme.domain.repository

import com.bsstokes.acme.domain.model.InputData
import com.bsstokes.acme.domain.response.SimpleResponse

interface InputDataRepository {
    suspend fun getInputData(): SimpleResponse<InputData>
}
