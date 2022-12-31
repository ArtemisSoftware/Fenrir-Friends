package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.composables.grid.FFStaggeredVerticalGrid
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.toolbar.FFToolBar
import com.artemissoftware.core_ui.composables.toolbar.FFToolbarAction
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.screen.gallery.composables.BreedCard
import com.artemissoftware.fenrirfriends.screen.gallery.composables.GalleryGrid
import com.artemissoftware.fenrirfriends.screen.gallery.composables.GalleryList
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel
) {

    val state = viewModel.state.collectAsState()

    BuildGalleryScreen(state = state.value, events = viewModel::onTriggerEvent)
}

@Composable
private fun BuildGalleryScreen(
    state: GalleryState,
    events: ((GalleryEvents) -> Unit)? = null
) {

    FFScaffold(
        isLoading = state.isLoading,
        toolbar = {
            FFToolBar(
                toolbarActions = {
                    ToolbarActions(
                        onReorderClicked = {
                            events?.invoke(GalleryEvents.ReorderAlphabetic)
                        },
                        onViewClicked = {
                            events?.invoke(GalleryEvents.ChangeView)
                        },
                    )
                }
            )
        },
        content =  {

            when(state.viewType){
                GalleryViewType.LIST -> {
                    GalleryList(state = state, events = events)
                }
                GalleryViewType.GRID -> {
                    GalleryGrid(state = state, events = events)
                }
            }

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
private fun BuildGalleryScreenPreview() {

    val state = GalleryState(breeds = Breed.mockBreeds)
    BuildGalleryScreen(state, events = {})
}
