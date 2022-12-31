package com.artemissoftware.fenrirfriends.screen.gallery

import com.artemissoftware.domain.models.Breed

data class GalleryState(
    val breeds: List<Breed> = emptyList(),
    val isLoading: Boolean = false,
)