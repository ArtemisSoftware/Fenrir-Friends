package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.models.data.DataResponse

interface BreedRepository {

    suspend fun getBreeds(limit: Int, page: Int) : DataResponse<List<Breed>>
    suspend fun searchBreed(query: String) : DataResponse<List<Breed>>
}