package com.artemissoftware.fenrirfriends.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes.HomeGraph
import com.artemissoftware.fenrirfriends.screen.breedsearch.BreedSearchRoute
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryRoute


@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
    scaffoldState: FFUiScaffoldState,
    windowSize: WindowSize
) {

    NavHost(
        navController = navController,
        route = HomeGraph.graph,
        startDestination = HomeGraph.startDestination
    ) {

        GalleryRoute.composable(navGraphBuilder = this, scaffoldState = scaffoldState, navController = navController, windowSize = windowSize)

        BreedSearchRoute.composable(navGraphBuilder = this, scaffoldState = scaffoldState, navController = navController, windowSize = windowSize)

        detailNavigationGraph(navController = navController, scaffoldState = scaffoldState, windowSize = windowSize)
    }
}