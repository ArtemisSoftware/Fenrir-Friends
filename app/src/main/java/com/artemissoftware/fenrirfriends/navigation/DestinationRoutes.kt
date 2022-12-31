package com.artemissoftware.fenrirfriends.navigation

class DestinationRoutes {

    object HomeGraph : NavigationGraph {

        override val graph = "home_graph"
        override val startDestination = Destination.Gallery.route

        val gallery = Destination.Gallery
    }
}