package com.artemissoftware.data.mappers

import com.artemissoftware.data.dabase.entities.BreedEntity
import com.artemissoftware.data.remote.dto.BreedDto
import com.artemissoftware.domain.models.Breed

fun BreedDto.toBreed() : Breed{

    return Breed(
        id = id,
        url = imageDto.url,
        name = name,
        origin = origin,
        group = breedGroup,
        temperament = temperament
    )

}

fun BreedEntity.toBreed() : Breed{

    return Breed(
        id = id,
        url = url,
        name = name,
        origin = origin,
        group = group,
        temperament = temperament
    )
}

fun BreedDto.toEntity() : BreedEntity{

    return BreedEntity(
        id = id,
        url = imageDto.url,
        name = name,
        origin = origin,
        group = breedGroup,
        temperament = temperament
    )

}

fun List<BreedDto>.toEntity() : List<BreedEntity> {
    return this.map {
        return@map it.toEntity()
    }
}