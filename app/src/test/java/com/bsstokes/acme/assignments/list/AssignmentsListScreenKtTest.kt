package com.bsstokes.acme.assignments.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.bsstokes.acme.test.test
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AssignmentsListScreenKtTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun `shows Assignments`() = composeRule.test {
        setContent { AssignmentsListScreen() }
        onNodeWithText("Assignments").assertIsDisplayed()
    }
}
