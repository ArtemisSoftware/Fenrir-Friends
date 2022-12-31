package com.artemissoftware.fenrirfriends.screen.breeddetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes
import com.artemissoftware.fenrirfriends.navigation.NavigationRoute

object BreedDetailRoute: NavigationRoute<BreedDetailEvents, BreedDetailViewModel> {

    override fun getDestination() = DestinationRoutes.DetailGraph.detailBreed

    @Composable
    override fun viewModel(): BreedDetailViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: BreedDetailViewModel) = BreedDetailScreen(viewModel = viewModel)
}