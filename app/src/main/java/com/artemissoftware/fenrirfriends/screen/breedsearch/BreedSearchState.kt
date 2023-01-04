package com.artemissoftware.fenrirfriends.screen.breedsearch

import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.domain.models.Breed

data class BreedSearchState(
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
)
