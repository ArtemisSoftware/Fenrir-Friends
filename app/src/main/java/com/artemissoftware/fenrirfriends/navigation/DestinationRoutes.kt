package com.artemissoftware.fenrirfriends.navigation

class DestinationRoutes {

    object HomeGraph : NavigationGraph {

        override val graph = "home_graph"
        override val startDestination = Destination.Gallery.route

        val gallery = Destination.Gallery
    }

    object DetailGraph : NavigationGraph {

        override val graph = "detail_graph"
        override val startDestination = Destination.BreedDetail.route

        val detailBreed = Destination.BreedDetail
    }
}