package com.artemissoftware.data.remote

import com.artemissoftware.data.remote.dto.BreedDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {

    @GET("v1/breeds")
    suspend fun getBreeds(@Query("limit") limit: Int, @Query("page") page: Int): List<BreedDto>
}