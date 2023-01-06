package com.artemissoftware.fenrirfriends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.artemissoftware.core_ui.composables.window.rememberWindowSize
import com.artemissoftware.fenrirfriends.navigation.graphs.RootNavigationGraph
import com.artemissoftware.fenrirfriends.ui.theme.FenrirFriendsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FenrirFriendsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    RootNavigationGraph(
                        navController = rememberNavController(),
                        windowSize = rememberWindowSize(),
                        scaffoldState = viewModel.scaffoldState
                    )

                }
            }
        }
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
