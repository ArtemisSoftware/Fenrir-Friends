package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.fenrirfriends.navigation.DestinationRoutes
import com.artemissoftware.fenrirfriends.navigation.NavigationRoute

object GalleryRoute: NavigationRoute<GalleryEvents, GalleryViewModel> {

    override fun getDestination() = DestinationRoutes.HomeGraph.gallery

    @Composable
    override fun viewModel(): GalleryViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: GalleryViewModel, windowSize: WindowSize) = GalleryScreen(viewModel = viewModel, windowSize)
}