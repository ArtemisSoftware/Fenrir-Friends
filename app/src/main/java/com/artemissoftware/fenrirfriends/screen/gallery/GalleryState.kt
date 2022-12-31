package com.artemissoftware.fenrirfriends.screen.gallery

import com.artemissoftware.domain.models.Breed

data class GalleryState(
    val breeds: List<Breed> = listOf(),
    val isLoading: Boolean = false,
)
