package com.artemissoftware.data.remote.source

import com.artemissoftware.data.remote.dto.BreedDto

interface DogApiSource {

    suspend fun getBreeds(): List<BreedDto>
}