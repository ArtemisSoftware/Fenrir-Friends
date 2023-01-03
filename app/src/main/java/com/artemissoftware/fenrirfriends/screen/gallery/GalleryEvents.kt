package com.artemissoftware.fenrirfriends.screen.gallery

import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents

sealed class GalleryEvents: FFBaseEvents() {

    data class GoToBreedDetail(val breed: Breed) : GalleryEvents()
    object ReorderAlphabetic: GalleryEvents()
    object ChangeView: GalleryEvents()

    data class Reload(val ex: FenrisFriendsNetworkException, val reloadEvent: () ->Unit): GalleryEvents()
    data class ShowLoading(val loading: Boolean) : GalleryEvents()
}
