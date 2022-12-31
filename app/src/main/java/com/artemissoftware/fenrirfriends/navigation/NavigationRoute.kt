package com.artemissoftware.fenrirfriends.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.fenrirfriends.base.FFBaseEventViewModel
import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents
import com.artemissoftware.fenrirfriends.composables.navigation.ManageUIEvents

interface NavigationRoute<E: FFBaseEvents, T : FFBaseEventViewModel<E>> {

    fun getDestination(): Destination

    fun requiresAuthentication(): Boolean = false

    /**
     * Returns the screen's ViewModel. Needs to be overridden so that Hilt can generate code for the factory for the ViewModel class.
     */
    @Composable
    fun viewModel(): T


    /**
     * Returns the screen's content.
     */
    @Composable
    fun Content(viewModel: T)

    /**
     * Generates the composable for this route.
     */
    fun composable(
        navGraphBuilder: NavGraphBuilder,
        scaffoldState: FFUiScaffoldState,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(
            route = getDestination().getRouteInFull(),
            arguments = getDestination().arguments,
        ) {

            val context = LocalContext.current

            val viewModel = viewModel()

            Content(viewModel)

            ManageUIEvents(
                uiEvent = viewModel.uiEvent,
                scaffoldState = scaffoldState,
                onNavigatePopUpTo = { event->
                    navController.popBackStack(event.destinationRoute, inclusive = false, saveState = false)
                },
                onPopBackStack = {
                    navController.popBackStack()
                },
                onNavigate =  { event->
                    navController.navigate(event.route)
                },
                onChangeCurrentPositionBottomBar = { event->
                    scaffoldState.changeCurrentPositionBottomBar(event.destination, navController = navController)
                },
            )
        }
    }


}