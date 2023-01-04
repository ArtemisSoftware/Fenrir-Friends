package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.artemissoftware.core_ui.composables.scaffold.FFScaffold
import com.artemissoftware.core_ui.composables.toolbar.FFToolBar
import com.artemissoftware.core_ui.composables.toolbar.FFToolbarAction
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.composables.paging.HandlePagingResult
import com.artemissoftware.fenrirfriends.screen.gallery.composables.BreedGallery
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel
) {

    val state = viewModel.state.collectAsState()
    val breedsPagingItems: LazyPagingItems<Breed> = viewModel.breeds.collectAsLazyPagingItems()

    BuildGalleryScreen(
        state = state.value,
        events = viewModel::onTriggerEvent,
        pagingItems = breedsPagingItems
    )

}

@Composable
private fun BuildGalleryScreen(
    state: GalleryState,
    events: ((GalleryEvents) -> Unit)? = null,
    pagingItems: LazyPagingItems<Breed>
) {

    FFScaffold(
        isLoading = state.isLoading,
        toolbar = {
            FFToolBar(
                iconColor = Color.White,
                toolbarActions = { iconColor->
                    ToolbarActions(
                        iconColor = iconColor,
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

            HandlePagingResult(
                items = pagingItems,
                loading = {
                    events?.invoke(GalleryEvents.ShowLoading(it))
                },
                content = {
                    BreedGallery(
                        pagingItems = pagingItems,
                        state = state,
                        onItemClick = {
                            events?.invoke(GalleryEvents.GoToBreedDetail(it))
                        }
                    )
                },
                errorEvent = {
                    events?.invoke(GalleryEvents.Reload(
                        ex = it,
                        reloadEvent = { pagingItems.refresh() }
                    ))
                }

            )

        }
    )
}


@Composable
fun RowScope.ToolbarActions(
    onReorderClicked: () -> Unit,
    onViewClicked: () -> Unit,
    galleryViewType: GalleryViewType,
    iconColor: Color
) {
        FFToolbarAction(
            imageVector = Icons.Filled.MoreVert,
            tint = iconColor,
            onClicked = { onReorderClicked() }
        )
        FFToolbarAction(
            imageVector = if(galleryViewType == GalleryViewType.LIST) Icons.Default.List else Icons.Default.Refresh,
            tint = iconColor,
            onClicked = { onViewClicked() }
        )

}




@Preview(showBackground = true)
@Composable
private fun BuildGalleryScreenPreview() {

    //val state = GalleryState(breeds = Breed.mockBreeds)
    //BuildGalleryScreen(state, events = {}, allHeroes)
}
