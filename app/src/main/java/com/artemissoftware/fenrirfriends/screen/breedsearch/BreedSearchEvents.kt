package com.artemissoftware.fenrirfriends.screen.breedsearch

import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents
import com.artemissoftware.fenrirfriends.screen.models.BreedUi

sealed class BreedSearchEvents: FFBaseEvents() {

    data class GoToBreedDetail(val breed: Breed) : BreedSearchEvents()
    data class SearchBreed(val query: String) : BreedSearchEvents()
}

