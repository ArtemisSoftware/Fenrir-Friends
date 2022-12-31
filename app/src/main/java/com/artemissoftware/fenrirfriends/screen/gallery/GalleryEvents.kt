package com.artemissoftware.fenrirfriends.screen.gallery

import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents
import com.artemissoftware.fenrirfriends.screen.models.BreedUi

sealed class GalleryEvents: FFBaseEvents() {

    data class GoToBreedDetail(val breedUi: BreedUi) : GalleryEvents()
}
