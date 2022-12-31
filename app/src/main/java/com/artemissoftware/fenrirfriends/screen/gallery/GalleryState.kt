package com.artemissoftware.fenrirfriends.screen.gallery

import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.screen.gallery.models.GalleryViewType

data class GalleryState(
    val breeds: List<Breed> = emptyList(),
    val isLoading: Boolean = false,
    val viewType: GalleryViewType = GalleryViewType.LIST,
)
