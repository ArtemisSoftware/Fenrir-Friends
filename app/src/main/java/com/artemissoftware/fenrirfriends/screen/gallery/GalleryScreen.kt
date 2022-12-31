package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.toolbar.FFToolBar
import com.artemissoftware.core_ui.composables.toolbar.FFToolbarAction
import com.artemissoftware.domain.models.Breed
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
                        galleryViewType = state.viewType,
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
                    GalleryList(
                        breeds = state.breeds,
                        onItemClick = {
                            events?.invoke(GalleryEvents.GoToBreedDetail(it))
                        }
                    )
                }
                GalleryViewType.GRID -> {
                    GalleryGrid(
                        breeds = state.breeds,
                        onItemClick = {
                            events?.invoke(GalleryEvents.GoToBreedDetail(it))
                        }
                    )
                }
            }

        }
    )
}



@Composable
fun RowScope.ToolbarActions(
    onReorderClicked: () -> Unit,
    onViewClicked: () -> Unit,
    galleryViewType: GalleryViewType
) {
        FFToolbarAction(
            imageVector = Icons.Filled.MoreVert,
            tint = Color.White,
            onClicked = { onReorderClicked() }
        )
        FFToolbarAction(
            imageVector = if(galleryViewType == GalleryViewType.LIST) Icons.Default.List else Icons.Default.Refresh,
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
