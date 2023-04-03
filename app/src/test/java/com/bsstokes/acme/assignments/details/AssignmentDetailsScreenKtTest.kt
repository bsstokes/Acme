package com.bsstokes.acme.assignments.details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.bsstokes.acme.test.test
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AssignmentDetailsScreenKtTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun `displays driver name`() = composeRule.test {
        setContent {
            AssignmentDetailsScreen(
                driverName = "Driver Name",
                shipmentAddress = "Shipment Address",
            )
        }

        onNodeWithText("Driver Name").assertIsDisplayed()
    }

    @Test
    fun `displays shipment address`() = composeRule.test {
        setContent {
            AssignmentDetailsScreen(
                driverName = "Driver Name",
                shipmentAddress = "Shipment Address",
            )
        }

        onNodeWithText("Shipment Address").assertIsDisplayed()
    }

}
