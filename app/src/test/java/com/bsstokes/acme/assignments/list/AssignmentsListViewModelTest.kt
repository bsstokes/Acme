package com.bsstokes.acme.assignments.list

import app.cash.turbine.test
import com.bsstokes.acme.app.domain.model.Assignment
import com.bsstokes.acme.app.domain.model.Driver
import com.bsstokes.acme.app.domain.model.Shipment
import com.bsstokes.acme.app.domain.response.ErrorResponse
import com.bsstokes.acme.app.domain.response.SimpleResponse
import com.bsstokes.acme.app.domain.response.success
import com.bsstokes.acme.app.domain.usecase.LoadAssignmentsUseCase
import com.bsstokes.acme.test.MainDispatcherRule
import com.bsstokes.acme.test.shouldEqual
import com.bsstokes.acme.ui.ErrorUiState
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
        AssignmentsListViewModel(
            loadAssignmentsUseCase = object : LoadAssignmentsUseCase {
                override suspend fun loadAssignments(): SimpleResponse<List<Assignment>> {
                    return emptyList<Assignment>().success()
                }
            }
        ).uiState.test {
            awaitItem() shouldEqual LoadingUiState
        }
    }

    @Test
    fun `emits error when use case returns error`() = runTest {
        val loadAssignmentsUseCase = object : LoadAssignmentsUseCase {
            override suspend fun loadAssignments(): SimpleResponse<List<Assignment>> {
                return ErrorResponse
            }
        }

        AssignmentsListViewModel(
            loadAssignmentsUseCase = loadAssignmentsUseCase,
        ).uiState.test {
            skipItems(1)
            awaitItem() shouldEqual ErrorUiState
        }
    }

    @Test
    fun `emits content when use case returns success`() = runTest {
        val assignments = listOf(
            Assignment(driver = Driver("D1"), shipment = Shipment("S1")),
            Assignment(driver = Driver("D2"), shipment = Shipment("S2")),
        )

        val loadAssignmentsUseCase = object : LoadAssignmentsUseCase {
            override suspend fun loadAssignments(): SimpleResponse<List<Assignment>> {
                return assignments.success()
            }
        }

        AssignmentsListViewModel(
            loadAssignmentsUseCase = loadAssignmentsUseCase,
        ).uiState.test {
            skipItems(1)
            awaitItem() shouldEqual assignments.toUiState().content()
        }
    }
}
