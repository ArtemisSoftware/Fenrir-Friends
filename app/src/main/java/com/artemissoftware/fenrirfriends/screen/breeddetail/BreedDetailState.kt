package com.artemissoftware.fenrirfriends.screen.breeddetail

import com.artemissoftware.domain.models.Breed

data class BreedDetailState(
    var breed: Breed? = null,
    val isLoading: Boolean = false,
)
