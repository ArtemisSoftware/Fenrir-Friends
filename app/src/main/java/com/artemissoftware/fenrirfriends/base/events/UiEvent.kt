package com.artemissoftware.fenrirfriends.base.events

import com.artemissoftware.core_ui.composables.dialog.models.FFDialogType
import com.artemissoftware.core_ui.navigation.models.BaseDestination

sealed class UiEvent {

    data class ShowDialog(val dialogType: FFDialogType): UiEvent()

    object PopBackStack: UiEvent()

    data class Navigate(val route: String): UiEvent()
    data class NavigatePopUpTo(val currentRoute: String, val destinationRoute: String): UiEvent()

    data class ChangeCurrentPositionBottomBar(val destination: BaseDestination): UiEvent()
}