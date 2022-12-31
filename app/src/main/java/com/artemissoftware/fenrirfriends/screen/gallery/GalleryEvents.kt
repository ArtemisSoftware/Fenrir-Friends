package com.artemissoftware.fenrirfriends.screen.gallery

import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents

sealed class GalleryEvents: FFBaseEvents() {

    data class GoToBreedDetail(val breed: Breed) : GalleryEvents()
    object ReorderAlphabetic: GalleryEvents()
    object ChangeView: GalleryEvents()
}
