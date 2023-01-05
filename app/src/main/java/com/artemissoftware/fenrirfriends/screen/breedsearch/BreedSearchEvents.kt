package com.artemissoftware.fenrirfriends.screen.breedsearch

import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents

sealed class BreedSearchEvents: FFBaseEvents() {

    data class GoToBreedDetail(val breed: Breed) : BreedSearchEvents()
    data class SearchBreed(val query: String) : BreedSearchEvents()
    object OpenSearch : BreedSearchEvents()
    object CloseSearch : BreedSearchEvents()
    data class UpdateSearch(val text: String) : BreedSearchEvents()

    data class Reload(val ex: FenrisFriendsNetworkException, val reloadEvent: () ->Unit): BreedSearchEvents()
    object RepeatLastSearch : BreedSearchEvents()
}

