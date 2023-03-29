package com.bsstokes.acme.assignments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsstokes.acme.app.domain.usecase.LoadAssignmentsUseCase
import com.bsstokes.acme.ui.ErrorUiState
import com.bsstokes.acme.ui.LoadingUiState
import com.bsstokes.acme.ui.SimpleUiState
import com.bsstokes.acme.ui.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AssignmentsListViewModel @Inject constructor(
    loadAssignmentsUseCase: LoadAssignmentsUseCase,
) : ViewModel() {
    val uiState: StateFlow<SimpleUiState<AssignmentsListUiState>> = flow {
        val uiState = loadAssignmentsUseCase.loadAssignments().fold(
            ifSuccess = { it.toUiState().content() },
            ifError = { ErrorUiState }
        )
        emit(uiState)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), LoadingUiState)
}
