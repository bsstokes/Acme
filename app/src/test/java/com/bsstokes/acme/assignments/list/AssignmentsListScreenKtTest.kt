package com.bsstokes.acme.assignments.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.bsstokes.acme.test.shouldEqual
import com.bsstokes.acme.test.test
import com.bsstokes.acme.ui.ErrorUiState
import com.bsstokes.acme.ui.ErrorViewTags
import com.bsstokes.acme.ui.LoadingUiState
import com.bsstokes.acme.ui.LoadingViewTags
import com.bsstokes.acme.ui.content
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AssignmentsListScreenKtTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun `top-level composable with Loading shows loading view`() = composeRule.test {
        setContent {
            AssignmentsListScreen(
                uiState = LoadingUiState,
            )
        }

        onNodeWithTag(LoadingViewTags.loading).assertIsDisplayed()
    }

    @Test
    fun `top-level composable with Content shows content view`() = composeRule.test {
        setContent {
            AssignmentsListScreen(
                uiState = AssignmentsListUiState(assignments = emptyList()).content(),
            )
        }

        onNodeWithTag(AssignmentsListScreenTags.content).assertIsDisplayed()
    }

    @Test
    fun `top-level composable with Error shows error view`() = composeRule.test {
        setContent {
            AssignmentsListScreen(
                uiState = ErrorUiState,
            )
        }

        onNodeWithTag(ErrorViewTags.error).assertIsDisplayed()
    }

    @Test
    fun `content shows driver names`() = composeRule.test {
        val uiState = AssignmentsListUiState(
            assignments = listOf(
                AssignmentItem(
                    driverName = "Driver #1",
                    shipmentAddress = "Address #1",
                ),
                AssignmentItem(
                    driverName = "Driver #2",
                    shipmentAddress = "Address #2",
                ),
            )
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
        var callbackCalledWithData: Pair<String, String>? = null

        val uiState = AssignmentsListUiState(
            assignments = listOf(
                AssignmentItem(
                    driverName = "Driver #1",
                    shipmentAddress = "Address #1",
                ),
                AssignmentItem(
                    driverName = "Driver #2",
                    shipmentAddress = "Address #2",
                ),
            )
        )

        setContent {
            AssignmentsListScreen(
                uiState = uiState,
                onSelectAssignment = { driverName, shipmentAddress ->
                    callbackCalledWithData = driverName to shipmentAddress
                },
            )
        }

        onNodeWithText("Driver #2").assertIsDisplayed().performClick()
        callbackCalledWithData shouldEqual ("Driver #2" to "Address #2")
    }
}
