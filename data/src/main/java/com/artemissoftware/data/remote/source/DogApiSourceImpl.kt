package com.artemissoftware.data.remote.source

import com.artemissoftware.data.remote.DogApi
import com.artemissoftware.data.remote.HandleApi
import com.artemissoftware.data.remote.dto.BreedDto
import javax.inject.Inject

class DogApiSourceImpl @Inject constructor (private val dogApi: DogApi): DogApiSource {

    override suspend fun getBreeds(): List<BreedDto> {
        return HandleApi.safeApiCall { dogApi.getBreeds() }
    }

    override suspend fun getBreeds(limit: Int, page: Int): List<BreedDto> {
        return HandleApi.safeApiCall { dogApi.getBreeds(limit = limit, page = page) }
    }

}