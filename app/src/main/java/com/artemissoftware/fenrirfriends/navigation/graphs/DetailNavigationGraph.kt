package com.artemissoftware.fenrirfriends.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes.DetailGraph
import com.artemissoftware.fenrirfriends.screen.breeddetail.BreedDetailRoute

fun NavGraphBuilder.detailNavigationGraph(
    navController: NavHostController,
    scaffoldState: FFUiScaffoldState
) {

    navigation(
        route = DetailGraph.graph,
        startDestination = DetailGraph.startDestination
    ) {

        BreedDetailRoute.composable(navGraphBuilder = this, scaffoldState = scaffoldState, navController = navController)
    }
}