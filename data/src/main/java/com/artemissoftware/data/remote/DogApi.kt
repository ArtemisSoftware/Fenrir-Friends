package com.artemissoftware.data.remote

import com.artemissoftware.data.remote.dto.BreedDto
import retrofit2.http.GET

interface DogApi {

    @GET("v1/breeds")
    suspend fun getBreeds(): List<BreedDto>

}