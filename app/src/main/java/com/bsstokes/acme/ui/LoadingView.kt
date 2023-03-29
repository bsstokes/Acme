package com.bsstokes.acme.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.testTag(LoadingViewTags.loading),
    ) {
        CircularProgressIndicator(
            modifier.align(Alignment.Center)
        )
    }
}

object LoadingViewTags {
    const val loading = "LoadingView.loading"
}
