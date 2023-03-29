package com.bsstokes.acme.assignments.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bsstokes.acme.ui.theme.AcmeTheme

@Composable
fun AssignmentsListScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text("Assignments")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAssignmentsListScreen() = AcmeTheme {
    AssignmentsListScreen()
}
