package com.artemissoftware.data.mappers

import com.artemissoftware.data.remote.dto.BreedDto
import com.artemissoftware.domain.models.Breed

fun BreedDto.toBreed() : Breed{

    return Breed(
        id = id,
        url = imageDto.url,
        name = name
    )

}