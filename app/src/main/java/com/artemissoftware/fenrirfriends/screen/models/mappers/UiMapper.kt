package com.artemissoftware.fenrirfriends.screen.models.mappers

import com.artemissoftware.domain.models.Breed
import com.artemissoftware.fenrirfriends.screen.models.BreedUi

fun Breed.toUI(): BreedUi {
    return BreedUi(
        id = id,
        name = name,
        url = url,
        group = group,
        origin = origin,
        temperament = temperament
    )
}