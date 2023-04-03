package com.bsstokes.acme.assignments.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.bsstokes.acme.test.test
import com.bsstokes.acme.ui.ErrorUiState
import com.bsstokes.acme.ui.ErrorViewTags
import com.bsstokes.acme.ui.LoadingUiState
import com.bsstokes.acme.ui.LoadingViewTags
import com.bsstokes.acme.ui.content
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AssignmentsListScreenKtTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun `clicking on item calls top-level callback`() = composeRule.test {
        val viewModel: AssignmentsListViewModel = mockk {
            every { uiState } returns MutableStateFlow(
                AssignmentsListUiState(assignments = assignmentItems()).content()
            )
        }
        val navigateToAssignment: (String, String) -> Unit = spyk()

        setContent {
            AssignmentsListScreen(
                viewModel = viewModel,
                navigateToAssignment = navigateToAssignment,
            )
        }

        onNodeWithText("Driver #2").assertIsDisplayed().performClick()
        verify { navigateToAssignment("Driver #2", "Address #2") }
    }

    @Test
    fun `top-level composable with Loading shows loading view`() = composeRule.test {
        setContent {
            AssignmentsListScreen(
                uiState = LoadingUiState,
                onSelectAssignment = { _, _ -> },
            )
        }

        onNodeWithTag(LoadingViewTags.loading).assertIsDisplayed()
    }

    @Test
    fun `top-level composable with Content shows content view`() = composeRule.test {
        setContent {
            AssignmentsListScreen(
                uiState = AssignmentsListUiState(assignments = emptyList()).content(),
                onSelectAssignment = { _, _ -> },
            )
        }

        onNodeWithTag(AssignmentsListScreenTags.content).assertIsDisplayed()
    }

    @Test
    fun `top-level composable with Error shows error view`() = composeRule.test {
        setContent {
            AssignmentsListScreen(
                uiState = ErrorUiState,
                onSelectAssignment = { _, _ -> },
            )
        }

        onNodeWithTag(ErrorViewTags.error).assertIsDisplayed()
    }

    @Test
    fun `content shows driver names`() = composeRule.test {
        val uiState = AssignmentsListUiState(
            assignments = assignmentItems(),
        )

        setContent {
            AssignmentsListScreen(
                uiState = uiState,
                onSelectAssignment = { _, _ -> }
            )
        }

        onNodeWithText("Driver #1").assertIsDisplayed()
        onNodeWithText("Driver #2").assertIsDisplayed()
    }

    @Test
    fun `clicking on item calls callback`() = composeRule.test {
        val callback: (String, String) -> Unit = spyk()

        val uiState = AssignmentsListUiState(
            assignments = assignmentItems(),
        )

        setContent {
            AssignmentsListScreen(
                uiState = uiState,
                onSelectAssignment = callback,
            )
        }

        onNodeWithText("Driver #2").assertIsDisplayed().performClick()
        verify { callback("Driver #2", "Address #2") }
    }

    private fun assignmentItems() = listOf(
        AssignmentItem(
            driverName = "Driver #1",
            shipmentAddress = "Address #1",
        ),
        AssignmentItem(
            driverName = "Driver #2",
            shipmentAddress = "Address #2",
        ),
    )
}
