package com.artemissoftware.fenrirfriends.screen.breedsearch

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.toolbar.FFSearchToolBar
import com.artemissoftware.core_ui.composables.toolbar.FFToolbarAction
import com.artemissoftware.core_ui.composables.toolbar.models.FFSearchToolBarState
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.composables.toolbar.SearchToolbar
import com.artemissoftware.fenrirfriends.screen.gallery.composables.GalleryList


@Composable
fun BreedSearchScreen(
    viewModel: BreedSearchViewModel
) {

    val state = viewModel.state.collectAsState()

    BuildBreedSearchScreen(
        state = state.value,
        events = viewModel::onTriggerEvent,
        searchAppBarState = viewModel.searchAppBarState.value,
        searchText = viewModel.searchTextState.value
    )
}

@Composable
private fun BuildBreedSearchScreen(
    state: BreedSearchState,
    searchText: String,
    searchAppBarState: FFSearchToolBarState,
    events: ((BreedSearchEvents) -> Unit)? = null
) {

    FFScaffold(
        isLoading = state.isLoading,
        toolbar = {

            SearchToolbar(
                iconColor = Color.White,
                searchAppBarState = searchAppBarState,
                searchTextState = searchText,
                onTextChanged = {
                    events?.invoke(BreedSearchEvents.UpdateSearch(it))
                },
                onSearchClicked = {
                    events?.invoke(BreedSearchEvents.OpenSearch)
                },
                onCloseClicked = {
                    events?.invoke(BreedSearchEvents.CloseSearch)
                },
                placeholderTextId = R.string.search_by_breed_name,
            )
        },
        content =  {

            GalleryList(
                breeds = state.breeds,
                onItemClick = {
                    events?.invoke(BreedSearchEvents.GoToBreedDetail(it))
                },
                BreedDetailType.RESUME
            )

        }
    )
}



@Composable
fun RowScope.ToolbarActions(
    onReorderClicked: () -> Unit,
    onViewClicked: () -> Unit
) {
    FFToolbarAction(
        imageVector = Icons.Filled.MoreVert,
        tint = Color.White,
        onClicked = { onReorderClicked() }
    )
    FFToolbarAction(
        imageVector = Icons.Filled.Create,
        tint = Color.White,
        onClicked = { onViewClicked() }
    )

}


@Preview(showBackground = true)
@Composable
private fun BuildBreedSearchScreenPreview() {

    val state = BreedSearchState(breeds = Breed.mockBreeds)
    BuildBreedSearchScreen(state, events = {}, searchText = "searchText",searchAppBarState = FFSearchToolBarState.OPENED )
}
