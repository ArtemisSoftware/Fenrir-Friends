package com.artemissoftware.fenrirfriends.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.fenrirfriends.navigation.graphs.HomeNavigationGraph

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: FFUiScaffoldState,
    windowSize: WindowSize
) {
    FFScaffold(
        ffUiScaffoldState = scaffoldState,
        bottomBarItems = scaffoldState.bottomBarDestinations.value,
        navController = navController,
        showConnectivityStatus = false,
        content =  {
            HomeNavigationGraph(navController = navController, scaffoldState = scaffoldState, windowSize = windowSize)
        }
    )
}