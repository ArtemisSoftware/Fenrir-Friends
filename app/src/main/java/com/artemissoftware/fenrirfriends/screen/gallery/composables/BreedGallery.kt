package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryState
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreedGallery(
    state: GalleryState,
    pagingItems: LazyPagingItems<Breed>,
    onItemClick: (Breed) -> Unit,
    windowSize: WindowSize,
    listState: LazyListState,
    gridState: LazyStaggeredGridState
) {

    when(state.viewType){
        GalleryViewType.LIST -> {
            GalleryList(
                listState = listState,
                windowSize = windowSize,
                breeds = pagingItems,
                onItemClick = onItemClick
            )
        }
        GalleryViewType.GRID -> {
            GalleryGrid(
                gridState = gridState,
                breeds = pagingItems,
                onItemClick = onItemClick
            )
        }
    }


}