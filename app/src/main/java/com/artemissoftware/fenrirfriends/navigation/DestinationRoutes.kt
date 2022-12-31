package com.artemissoftware.fenrirfriends.navigation

class DestinationRoutes {

    object RootGraph : NavigationGraph {

        override val graph = "root_graph"
        override val startDestination = Destination.Home.route

        val home = Destination.Home
    }

    object HomeGraph : NavigationGraph {

        override val graph = "home_graph"
        override val startDestination = Destination.Gallery.route

        val gallery = Destination.Gallery
        val breadSearch = Destination.BreedSearch
    }

    object DetailGraph : NavigationGraph {

        override val graph = "detail_graph"
        override val startDestination = Destination.BreedDetail.route

        val detailBreed = Destination.BreedDetail
    }
}