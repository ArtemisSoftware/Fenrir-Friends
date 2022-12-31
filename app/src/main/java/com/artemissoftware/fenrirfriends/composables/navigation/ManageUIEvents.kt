package com.artemissoftware.fenrirfriends.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.artemissoftware.core_ui.composables.scaffold.FFUiScaffoldState
import com.artemissoftware.fenrirfriends.base.events.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ManageUIEvents(
    uiEvent: Flow<UiEvent>,
    scaffoldState: FFUiScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit = {},
    onNavigatePopUpTo: (UiEvent.NavigatePopUpTo) -> Unit = {},
    onChangeCurrentPositionBottomBar: (UiEvent.ChangeCurrentPositionBottomBar) -> Unit = {},
    onPopBackStack: () -> Unit = {},
) {

    LaunchedEffect(key1 = Unit) {

        uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.ShowDialog -> {
                    scaffoldState.showDialog(event.dialogType)
                }
                is UiEvent.PopBackStack -> { onPopBackStack.invoke() }
                is UiEvent.Navigate -> { onNavigate(event) }
                is UiEvent.ChangeCurrentPositionBottomBar -> { onChangeCurrentPositionBottomBar(event) }
                is UiEvent.NavigatePopUpTo -> { onNavigatePopUpTo(event) }
            }
        }
    }
}