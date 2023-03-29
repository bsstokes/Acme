package com.bsstokes.acme.app.domain.usecase

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.success
import kotlinx.coroutines.delay

class LoadFakeAssignmentsUseCase : LoadAssignmentsUseCase {
    override suspend fun loadAssignments(): SimpleResponse<List<Assignment>> {
        delay(2_000)
        return FakeData.assignments.success()
    }
}
