package com.artemissoftware.fenrirfriends.screen.breedsearch

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes
import com.artemissoftware.fenrirfriends.navigation.NavigationRoute

object BreedSearchRoute: NavigationRoute<BreedSearchEvents, BreedSearchViewModel> {

    override fun getDestination() = DestinationRoutes.HomeGraph.breadSearch

    @Composable
    override fun viewModel(): BreedSearchViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: BreedSearchViewModel, windowSize: WindowSize) = BreedSearchScreen(viewModel = viewModel, windowSize = windowSize)
}