package com.bsstokes.acme

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bsstokes.acme.assignments.list.AssignmentsListScreen
import com.bsstokes.acme.ui.theme.AcmeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcmeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "list",
                    ) {
                        composable("list") {
                            AssignmentsListScreen(
                                viewModel = hiltViewModel(),
                                navigateToAssignment = { driverName, shipmentAddress ->
                                    Log.d("TEST", "Navigate to $driverName → $shipmentAddress")
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
