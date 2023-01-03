package com.artemissoftware.fenrirfriends.screen.gallery.composables

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.composables.breed.models.BreedDetailType
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryEvents
import com.artemissoftware.fenrirfriends.screen.gallery.GalleryState
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType

@Composable
fun BreedGallery(
    state: GalleryState,
    events: ((GalleryEvents) -> Unit)? = null
) {

    state.breeds_?.let { breeds ->
        when(state.viewType){
            GalleryViewType.LIST -> {
                GalleryList(
                    breeds = breeds,
                    onItemClick = {
                        events?.invoke(GalleryEvents.GoToBreedDetail(it))
                    }
                )
            }
            GalleryViewType.GRID -> {
                GalleryGrid(
                    breeds = breeds,
                    onItemClick = {
                        events?.invoke(GalleryEvents.GoToBreedDetail(it))
                    }
                )
            }
        }
    }

}