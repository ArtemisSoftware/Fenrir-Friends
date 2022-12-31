package com.artemissoftware.fenrirfriends.screen.breedsearch

import com.artemissoftware.domain.models.Breed

data class BreedSearchState(
    val breeds: List<Breed> = emptyList(),
    val isLoading: Boolean = false,
)
