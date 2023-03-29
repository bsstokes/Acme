package com.bsstokes.acme.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.bsstokes.acme.R

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.testTag(ErrorViewTags.error),
    ) {
        Text(
            text = stringResource(R.string.generic_error_message),
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

object ErrorViewTags {
    const val error = "ErrorView.error"
}
