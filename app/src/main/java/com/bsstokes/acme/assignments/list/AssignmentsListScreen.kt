package com.bsstokes.acme.assignments.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.bsstokes.acme.ui.ErrorView
import com.bsstokes.acme.ui.LoadingView
import com.bsstokes.acme.ui.SimpleUiState
import com.bsstokes.acme.ui.theme.AcmeTheme
import com.bsstokes.acme.ui.theme.Dimens
import com.bsstokes.acme.ui.theme.Typography

@Composable
fun AssignmentsListScreen(
    viewModel: AssignmentsListViewModel,
    navigateToAssignment: (driverName: String, shipmentAddress: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()

    AssignmentsListScreen(
        uiState = uiState,
        onSelectAssignment = navigateToAssignment,
        modifier = modifier,
    )
}

@Composable
fun AssignmentsListScreen(
    uiState: SimpleUiState<AssignmentsListUiState>,
    onSelectAssignment: (driverName: String, shipmentAddress: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    uiState.fold(
        isContent = {
            AssignmentsListScreen(
                uiState = it,
                modifier = modifier,
                onSelectAssignment = onSelectAssignment,
            )
        },
        ifLoading = { LoadingView(modifier = modifier) },
        ifError = { ErrorView(modifier = modifier) },
    )
}

@Composable
fun AssignmentsListScreen(
    uiState: AssignmentsListUiState,
    onSelectAssignment: (driverName: String, shipmentAddress: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag(AssignmentsListScreenTags.content),
    ) {
        items(uiState.assignments) { assignmentItem ->
            // Remember lambda so it doesn't cause recomposition
            val callback = remember(assignmentItem) {
                { onSelectAssignment(assignmentItem.driverName, assignmentItem.shipmentAddress) }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = callback)
                    .padding(Dimens.Padding.Base_100),
            ) {
                Text(assignmentItem.driverName)
                Text(assignmentItem.shipmentAddress, style = Typography.caption)
            }
        }
    }
}

object AssignmentsListScreenTags {
    const val content = "AssignmentsListScreen.content"
}

@Preview(showBackground = true)
@Composable
private fun PreviewAssignmentsListScreenContent() = AcmeTheme {
    AssignmentsListScreen(
        uiState = AssignmentsListUiState(
            assignments = listOf(
                AssignmentItem(
                    driverName = "Driver #1",
                    shipmentAddress = "Address #1",
                ),
                AssignmentItem(
                    driverName = "Driver #2",
                    shipmentAddress = "Address #2",
                ),
            ),
        ),
        onSelectAssignment = { _, _ -> },
    )
}
