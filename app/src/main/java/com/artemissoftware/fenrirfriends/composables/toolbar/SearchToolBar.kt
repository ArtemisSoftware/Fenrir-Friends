package com.artemissoftware.fenrirfriends.composables.toolbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.toolbar.FFSearchToolBar
import com.artemissoftware.core_ui.composables.toolbar.FFToolBar
import com.artemissoftware.core_ui.composables.toolbar.FFToolbarAction
import com.artemissoftware.core_ui.composables.toolbar.models.FFSearchToolBarState

@Composable
fun SearchToolbar(
    searchAppBarState: FFSearchToolBarState,
    searchText: String,
    onBackClicked: (() -> Unit)? = null,
    onTextChanged: (String) -> Unit,
    onOpenClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    onFocusChange: (FocusState) -> Unit = {},
    onSearchClicked: (String) -> Unit,
    toolbarActions: @Composable RowScope.() -> Unit = {},
    @StringRes placeholderTextId: Int,
    iconColor: Color = Color.Black
) {
    when (searchAppBarState) {
        FFSearchToolBarState.CLOSED -> {

            FFToolBar(
                onBackClicked = onBackClicked,
                iconColor = iconColor,
                toolbarActions = {
                    FFToolbarAction(
                        imageVector = Icons.Filled.Search,
                        tint = iconColor,
                        onClicked = onOpenClicked
                    )
                    toolbarActions()
                }
            )
        }
        else -> {
            FFSearchToolBar(
                iconColor = iconColor,
                text = searchText,
                onTextChange = { text ->
                    onTextChanged.invoke(text)
                },
                onFocusChange = onFocusChange,
                onCloseClicked =  onCloseClicked,
                onSearchClicked = { onSearchClicked.invoke(searchText) },
                placeholderTextId = placeholderTextId
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FFSearchToolBarCLOSEDPreview() {

    SearchToolbar(
        searchAppBarState = FFSearchToolBarState.CLOSED,
        searchText = "Search text",
        placeholderTextId = R.string.confirm,
        onOpenClicked = {},
        onCloseClicked = {},
        onTextChanged = {},
        onSearchClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun FFSearchToolBarOPENEDPreview() {

    SearchToolbar(
        searchAppBarState = FFSearchToolBarState.OPENED,
        searchText = "Search text",
        placeholderTextId = R.string.confirm,
        onOpenClicked = {},
        onCloseClicked = {},
        onTextChanged = {},
        onSearchClicked = {}
    )
}