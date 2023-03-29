package com.bsstokes.acme.assignments.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.bsstokes.acme.ui.ErrorView
import com.bsstokes.acme.ui.LoadingView
import com.bsstokes.acme.ui.SimpleUiState
import com.bsstokes.acme.ui.content
import com.bsstokes.acme.ui.theme.AcmeTheme

@Composable
fun AssignmentsListScreen(
    modifier: Modifier = Modifier,
) {
    val uiState = AssignmentsListUiState(
        assignments = listOf(
            AssignmentItem(
                driverName = "Driver #1",
                shippingAddress = "Address #1",
            ),
            AssignmentItem(
                driverName = "Driver #2",
                shippingAddress = "Address #2",
            ),
        )
    ).content()

    AssignmentsListScreen(
        uiState = uiState,
        modifier = modifier,
    )
}

@Composable
fun AssignmentsListScreen(
    uiState: SimpleUiState<AssignmentsListUiState>,
    modifier: Modifier = Modifier,
) {
    uiState.fold(
        isContent = { AssignmentsListScreen(uiState = it, modifier = modifier) },
        ifLoading = { LoadingView(modifier = modifier) },
        ifError = { ErrorView(modifier = modifier) },
    )
}

@Composable
fun AssignmentsListScreen(
    uiState: AssignmentsListUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag(AssignmentsListScreenTags.content),
    ) {
        items(uiState.assignments) { assignmentItem ->
            Text(assignmentItem.driverName)
        }
    }
}

object AssignmentsListScreenTags {
    const val content = "AssignmentsListScreen.content"
}

@Preview(showBackground = true)
@Composable
private fun PreviewAssignmentsListScreen() = AcmeTheme {
    AssignmentsListScreen()
}

@Preview(showBackground = true)
@Composable
private fun PreviewAssignmentsListScreenContent() = AcmeTheme {
    AssignmentsListScreen(
        uiState = AssignmentsListUiState(
            assignments = listOf(
                AssignmentItem(
                    driverName = "Driver #1",
                    shippingAddress = "Address #1",
                ),
                AssignmentItem(
                    driverName = "Driver #2",
                    shippingAddress = "Address #2",
                ),
            ),
        ),
    )
}
