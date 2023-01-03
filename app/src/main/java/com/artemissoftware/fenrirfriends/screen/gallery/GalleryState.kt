package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType

data class GalleryState(
    val isLoading: Boolean = false,
    val viewType: GalleryViewType = GalleryViewType.LIST,
)
