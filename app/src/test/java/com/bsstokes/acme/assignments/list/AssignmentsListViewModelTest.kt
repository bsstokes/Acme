package com.bsstokes.acme.assignments.list

import app.cash.turbine.test
import com.bsstokes.acme.test.MainDispatcherRule
import com.bsstokes.acme.test.shouldEqual
import com.bsstokes.acme.ui.LoadingUiState
import com.bsstokes.acme.ui.content
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AssignmentsListViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `starts as loading`() = runTest {
        AssignmentsListViewModel().uiState.test {
            awaitItem() shouldEqual LoadingUiState
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `shows content after loading`() = runTest {
        AssignmentsListViewModel().uiState.test {
            skipItems(1)
            awaitItem() shouldEqual FakeData.assignmentsListUiState.content()
            cancelAndIgnoreRemainingEvents()
        }
    }
}
