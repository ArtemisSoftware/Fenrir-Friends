package com.artemissoftware.fenrirfriends.composables.toolbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
    searchTextState: String,
    onBackClicked: (() -> Unit)? = null,
    onTextChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onCloseClicked: () -> Unit,
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
                        onClicked = onSearchClicked
                    )
                }
            )
        }
        else -> {
            FFSearchToolBar(
                iconColor = iconColor,
                text = searchTextState,
                onTextChange = { text ->
                    onTextChanged.invoke(text)
                },
                onCloseClicked =  onCloseClicked,
                onSearchClicked = {},
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
        searchTextState = "Search text",
        placeholderTextId = R.string.confirm,
        onSearchClicked = {},
        onCloseClicked = {},
        onTextChanged = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun FFSearchToolBarOPENEDPreview() {

    SearchToolbar(
        searchAppBarState = FFSearchToolBarState.OPENED,
        searchTextState = "Search text",
        placeholderTextId = R.string.confirm,
        onSearchClicked = {},
        onCloseClicked = {},
        onTextChanged = {}
    )
}