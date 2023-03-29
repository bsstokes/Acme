package com.bsstokes.acme.assignments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsstokes.acme.ui.LoadingUiState
import com.bsstokes.acme.ui.SimpleUiState
import com.bsstokes.acme.ui.content
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class AssignmentsListViewModel : ViewModel() {
    val uiState: StateFlow<SimpleUiState<AssignmentsListUiState>> =
        flow<SimpleUiState<AssignmentsListUiState>> {
            delay(2_000L)
            emit(FakeData.assignmentsListUiState.content())
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), LoadingUiState)
}
