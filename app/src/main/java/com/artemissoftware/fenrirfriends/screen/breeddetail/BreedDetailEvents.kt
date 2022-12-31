package com.artemissoftware.fenrirfriends.screen.breeddetail

import com.artemissoftware.fenrirfriends.base.events.FFBaseEvents


sealed class BreedDetailEvents: FFBaseEvents() {
    object PopBackStack: BreedDetailEvents()
}