package com.bsstokes.acme.assignments.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.bsstokes.acme.ui.SimpleUiState
import com.bsstokes.acme.ui.content
import com.bsstokes.acme.ui.theme.AcmeTheme

@Composable
fun AssignmentsListScreen(
    modifier: Modifier = Modifier,
) {
    val uiState = AssignmentsListUiState(title = "Assignments").content()

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
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(AssignmentsListScreenTags.content),
    ) {
        Text(
            text = uiState.title,
            modifier = Modifier.align(Alignment.Center),
        )
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
        uiState = AssignmentsListUiState(title = "Assignments"),
    )
}
