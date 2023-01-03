package com.artemissoftware.core_ui.composables.scaffold

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.core_ui.composables.scaffold.models.FFBottomBarState
import com.artemissoftware.core_ui.extensions.changeGraph
import com.artemissoftware.core_ui.navigation.models.BaseDestination
import com.artemissoftware.core_ui.navigation.models.BottomBarItem
import kotlinx.coroutines.CoroutineScope

open class FFUiScaffoldState(
    private val scope: CoroutineScope? = null
) {


    var dialog = mutableStateOf<FFDialogType?>(null)
        private set

    var bottomBarDestinations = mutableStateOf<List<BottomBarItem>>(emptyList())
        private set

    var selectedBottomBarItem = mutableStateOf<FFBottomBarState>(FFBottomBarState())
        private set

    var bottomSheet = mutableStateOf<Boolean>(false)
        private set



    fun showDialog(dialogType: FFDialogType) {
        dialog.value = dialogType
    }

    fun closeDialog() {
        dialog.value = null
    }

    fun expandBottomSheet() {
        bottomSheet.value = true
    }

    fun collapseBottomSheet() {
        bottomSheet.value = true
    }

    fun setBottomBarDestinations(items: List<BottomBarItem>) {
        bottomBarDestinations.value = items
    }



    fun changeCurrentPositionBottomBar(
        destination: BaseDestination,
        navController: NavHostController?
    ) {

        navController?.let {
            changeCurrentPositionBottomBar(
                destination = destination,
                route = destination.route,
                navController = it
            )
        }
    }



    fun getSelectedBottomBarDestination(defaultDestination: BaseDestination): BaseDestination {
        return selectedBottomBarItem.value.currentDestination ?: defaultDestination
    }



    private fun isBottomNavigationDestination(destination: BaseDestination) : Boolean{

        with(bottomBarDestinations.value) {

            if (isNotEmpty()) {
                return any { it.destination.getRouteInFull() == destination.getRouteInFull() }
            }
        }

        return false
    }


    private fun changeCurrentPositionBottomBar(
        destination: BaseDestination,
        route: String,
        navController: NavHostController
    ){
        with(bottomBarDestinations.value) {

            if (isNotEmpty()) {
                selectedBottomBarItem.value.update(
                    currentPosition = indexOfFirst { it.destination.route == destination.getRouteInFull() },
                    currentDestination = destination
                )
                navController.changeGraph(route)
            }
        }
    }

}