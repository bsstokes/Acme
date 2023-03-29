package com.bsstokes.acme.di

import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.success
import com.bsstokes.acme.app.domain.usecase.LoadAssignmentsUseCase
import com.bsstokes.acme.assignments.list.FakeData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun loadAssignmentsUseCase(): LoadAssignmentsUseCase = object : LoadAssignmentsUseCase {
        override suspend fun loadAssignments(): SimpleResponse<List<Assignment>> {
            delay(2_000)
            return FakeData.assignments.success()
        }
    }
}
