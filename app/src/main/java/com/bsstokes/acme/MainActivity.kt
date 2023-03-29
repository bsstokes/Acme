package com.bsstokes.acme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.bsstokes.acme.assignments.list.AssignmentsListScreen
import com.bsstokes.acme.assignments.list.AssignmentsListViewModel
import com.bsstokes.acme.ui.theme.AcmeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcmeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AssignmentsListScreen(AssignmentsListViewModel())
                }
            }
        }
    }
}
