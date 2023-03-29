package com.bsstokes.acme.assignments.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.bsstokes.acme.test.test
import com.bsstokes.acme.ui.LoadingUiState
import com.bsstokes.acme.ui.content
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AssignmentsListScreenKtTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun `top-level composable with Loading show loading view`() = composeRule.test {
        setContent {
            AssignmentsListScreen(
                uiState = LoadingUiState,
            )
        }

        onNodeWithTag(LoadingViewTags.loading).assertIsDisplayed()
    }

    @Test
    fun `top-level composable with Content show content view`() = composeRule.test {
        setContent {
            AssignmentsListScreen(
                uiState = AssignmentsListUiState(title = "TITLE").content(),
            )
        }

        onNodeWithTag(AssignmentsListScreenTags.content).assertIsDisplayed()
    }

    @Test
    fun `content shows title`() = composeRule.test {
        val uiState = AssignmentsListUiState(title = "TITLE")

        setContent {
            AssignmentsListScreen(
                uiState = uiState,
            )
        }

        onNodeWithText("TITLE").assertIsDisplayed()
    }
}
