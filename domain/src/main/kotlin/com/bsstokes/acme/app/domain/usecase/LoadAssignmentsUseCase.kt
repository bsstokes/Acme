package com.bsstokes.acme.app.domain.usecase

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.response.SimpleResponse

interface LoadAssignmentsUseCase {
    suspend fun loadAssignments(): SimpleResponse<List<Assignment>>
}
