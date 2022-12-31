package com.artemissoftware.fenrirfriends.screen.breedsearch

import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents
import com.artemissoftware.fenrirfriends.screen.models.BreedUi

sealed class BreedSearchEvents: FFBaseEvents() {

    data class GoToBreedDetail(val breedUi: BreedUi) : BreedSearchEvents()
}

