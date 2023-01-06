package com.artemissoftware.fenrirfriends.composables


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.artemissoftware.core_ui.composables.toolbar.models.FFSearchToolBarState
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.toolbar.SearchToolbar
import org.junit.Rule
import org.junit.Test

class SearchToolbarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchToolbar_addInputText_assertInputText() {

        val text = mutableStateOf("")

        composeTestRule.setContent {
            SearchToolbar(
                searchAppBarState = FFSearchToolBarState.OPENED,
                searchText = text.value,
                onTextChanged = {
                    text.value = it
                },
                onOpenClicked = {},
                onCloseClicked = {},
                onSearchClicked = {},
                placeholderTextId = R.string.search
            )
        }

        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("MyText")
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("MyText")
    }

    @Test
    fun openSearchToolbar_addInputText_pressCloseButtonOnce_assertEmptyInputText() {

        val text = mutableStateOf("")

        composeTestRule.setContent {
            SearchToolbar(
                searchAppBarState = FFSearchToolBarState.OPENED,
                searchText = text.value,
                onTextChanged = {
                    text.value = it
                },
                onOpenClicked = {},
                onCloseClicked = {},
                onSearchClicked = {},
                placeholderTextId = R.string.search
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("MyText")
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {

        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(FFSearchToolBarState.OPENED)

        composeTestRule.setContent {
            SearchToolbar(
                searchAppBarState = searchWidgetShown.value,
                searchText = text.value,
                onTextChanged = {
                    text.value = it
                },
                onOpenClicked = {},
                onCloseClicked = {
                    searchWidgetShown.value = FFSearchToolBarState.CLOSED
                },
                onSearchClicked = {},
                placeholderTextId = R.string.search
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("MyText")
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("FFSearchToolBar")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchToolbar_pressCloseButtonOnceWhenInputIsEmpty_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(FFSearchToolBarState.OPENED)
        composeTestRule.setContent {

            SearchToolbar(
                searchAppBarState = searchWidgetShown.value,
                searchText = text.value,
                onTextChanged = {
                    text.value = it
                },
                onOpenClicked = {},
                onCloseClicked = {
                    searchWidgetShown.value = FFSearchToolBarState.CLOSED
                },
                onSearchClicked = {},
                placeholderTextId = R.string.search
            )
        }
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("FFSearchToolBar")
            .assertDoesNotExist()
    }
}