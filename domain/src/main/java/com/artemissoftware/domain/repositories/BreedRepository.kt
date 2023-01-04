package com.artemissoftware.domain.repositories

import androidx.paging.PagingData
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.models.data.DataResponse
import kotlinx.coroutines.flow.Flow

interface BreedRepository {

    suspend fun getBreeds(limit: Int, page: Int) : DataResponse<List<Breed>>
    suspend fun searchBreed(query: String) : DataResponse<List<Breed>>
    fun getBreeds(): Flow<PagingData<Breed>>
    fun searchBreeds(query: String): Flow<PagingData<Breed>>

    suspend fun getBreed(id: Int): Breed?
}