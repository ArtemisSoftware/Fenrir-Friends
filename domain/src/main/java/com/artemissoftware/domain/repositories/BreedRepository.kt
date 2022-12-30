package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.models.data.DataResponse

interface BreedRepository {

    suspend fun getBreeds() : DataResponse<List<Breed>>
}