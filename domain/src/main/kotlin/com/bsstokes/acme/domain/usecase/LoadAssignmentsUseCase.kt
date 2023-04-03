package com.bsstokes.acme.domain.usecase

import com.bsstokes.acme.domain.model.Assignment
import com.bsstokes.acme.domain.response.SimpleResponse

interface LoadAssignmentsUseCase {
    suspend fun loadAssignments(): SimpleResponse<List<Assignment>>
}
