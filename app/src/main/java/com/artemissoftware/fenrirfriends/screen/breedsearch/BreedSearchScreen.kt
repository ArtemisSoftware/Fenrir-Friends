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
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.R
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.screen.gallery.composables.GalleryList


@Composable
fun BreedSearchScreen(
    viewModel: BreedSearchViewModel
) {

    val state = viewModel.state.collectAsState()

    BuildBreedSearchScreen(state = state.value, events = viewModel::onTriggerEvent)
}

@Composable
private fun BuildBreedSearchScreen(
    state: BreedSearchState,
    events: ((BreedSearchEvents) -> Unit)? = null
) {

    FFScaffold(
        isLoading = state.isLoading,
        toolbar = {

            FFSearchToolBar(
                text = "",
                placeholderTextId = R.string.search_by_breed_name,
                onTextChange = {

                },
                onCloseClicked = { /*TODO*/ },
                onSearchClicked = {

                }
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
    BuildBreedSearchScreen(state, events = {})
}
