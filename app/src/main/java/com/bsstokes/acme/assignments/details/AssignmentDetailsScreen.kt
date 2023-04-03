package com.bsstokes.acme.assignments.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bsstokes.acme.ui.theme.Dimens

@Composable
fun AssignmentDetailsScreen(
    driverName: String,
    shipmentAddress: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.Padding.Base_100),
    ) {
        Text(
            style = MaterialTheme.typography.h4,
            text = driverName,
        )

        Text(
            modifier = Modifier.padding(top = Dimens.Padding.Base_100),
            style = MaterialTheme.typography.body1,
            text = shipmentAddress,
        )
    }
}
