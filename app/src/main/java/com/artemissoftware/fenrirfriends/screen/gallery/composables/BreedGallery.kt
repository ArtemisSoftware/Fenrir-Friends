package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryEvents
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryState
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType

@Composable
fun BreedGallery(
    state: GalleryState,
    pagingItems: LazyPagingItems<Breed>,
    events: ((GalleryEvents) -> Unit)? = null
) {

    when(state.viewType){
        GalleryViewType.LIST -> {
            GalleryList(
                breeds = pagingItems,
                onItemClick = {
                    events?.invoke(GalleryEvents.GoToBreedDetail(it))
                }
            )
        }
        GalleryViewType.GRID -> {
            GalleryGrid(
                breeds = pagingItems,
                onItemClick = {
                    events?.invoke(GalleryEvents.GoToBreedDetail(it))
                }
            )
        }
    }


}