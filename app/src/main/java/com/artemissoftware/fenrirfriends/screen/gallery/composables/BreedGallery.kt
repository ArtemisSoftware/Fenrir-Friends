package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.core_ui.composables.window.models.WindowSize
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryEvents
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryState
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType

@Composable
fun BreedGallery(
    state: GalleryState,
    pagingItems: LazyPagingItems<Breed>,
    onItemClick: (Breed) -> Unit,
    windowSize: WindowSize
) {

    when(state.viewType){
        GalleryViewType.LIST -> {
            GalleryList(
                windowSize = windowSize,
                breeds = pagingItems,
                onItemClick = onItemClick
            )
        }
        GalleryViewType.GRID -> {
            GalleryGrid(
                breeds = pagingItems,
                onItemClick = onItemClick
            )
        }
    }


}