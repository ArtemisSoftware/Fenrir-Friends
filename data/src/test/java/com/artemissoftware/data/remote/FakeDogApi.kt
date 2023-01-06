package com.artemissoftware.data.remote

import com.artemissoftware.data.remote.dto.BreedDto

class FakeDogApi : DogApi {

    override suspend fun getBreeds(limit: Int, page: Int): List<BreedDto> {
        return FakeApiData.getBreedDto()
    }
}