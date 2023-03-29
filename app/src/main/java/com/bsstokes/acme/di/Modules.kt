package com.bsstokes.acme.di

import com.bsstokes.acme.app.domain.usecase.LoadAssignmentsUseCase
import com.bsstokes.acme.app.domain.usecase.LoadFakeAssignmentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun loadAssignmentsUseCase(): LoadAssignmentsUseCase = LoadFakeAssignmentsUseCase()
}
