package com.bsstokes.acme.di

import android.content.Context
import com.bsstokes.acme.algorithm.HungarianAssignmentAlgorithm
import com.bsstokes.acme.app.data.repository.JsonFileInputDataRepository
import com.bsstokes.acme.app.data.repository.JsonFileReader
import com.bsstokes.acme.data.AndroidJsonFileReader
import com.bsstokes.acme.domain.repository.InputDataRepository
import com.bsstokes.acme.domain.usecase.LoadAssignmentsFromRepositoryUseCase
import com.bsstokes.acme.domain.usecase.LoadAssignmentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun jsonFileReader(
        @ApplicationContext context: Context,
    ): JsonFileReader = AndroidJsonFileReader(
        context = context,
    )

    @Provides
    fun inputDataRepository(
        jsonFileReader: JsonFileReader,
    ): InputDataRepository = JsonFileInputDataRepository(
        jsonFileReader = jsonFileReader,
    )

    @Provides
    fun loadAssignmentsUseCase(
        inputDataRepository: InputDataRepository,
    ): LoadAssignmentsUseCase = LoadAssignmentsFromRepositoryUseCase(
        inputDataRepository = inputDataRepository,
        assignmentAlgorithm = HungarianAssignmentAlgorithm(),
    )
}
