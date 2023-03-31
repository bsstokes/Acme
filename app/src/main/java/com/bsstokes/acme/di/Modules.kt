package com.bsstokes.acme.di

import com.bsstokes.acme.app.domain.repository.FakeInputDataRepository
import com.bsstokes.acme.app.domain.repository.InputDataRepository
import com.bsstokes.acme.app.domain.usecase.LoadAssignmentsFromRepositoryUseCase
import com.bsstokes.acme.app.domain.usecase.LoadAssignmentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun inputDataRepository(): InputDataRepository = FakeInputDataRepository()

    @Provides
    fun loadAssignmentsUseCase(
        inputDataRepository: InputDataRepository,
    ): LoadAssignmentsUseCase = LoadAssignmentsFromRepositoryUseCase(
        inputDataRepository = inputDataRepository,
    )
}
