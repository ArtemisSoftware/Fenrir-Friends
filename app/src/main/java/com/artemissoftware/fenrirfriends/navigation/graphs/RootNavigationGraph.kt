package com.artemissoftware.fenrirfriends.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes.RootGraph
import com.artemissoftware.fenrirfriends.screen.home.HomeScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    scaffoldState: FFUiScaffoldState,
    windowSize: WindowSize
) {
    NavHost(
        navController = navController,
        route = RootGraph.graph,
        startDestination = RootGraph.startDestination
    ) {

        composable(route = RootGraph.home.getRouteInFull()) {
            HomeScreen(scaffoldState = scaffoldState, windowSize = windowSize)
        }
    }
}