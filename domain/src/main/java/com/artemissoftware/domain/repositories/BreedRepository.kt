package com.artemissoftware.domain.repositories

import androidx.paging.PagingData
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.models.data.DataResponse
import kotlinx.coroutines.flow.Flow

interface BreedRepository {

    fun getBreeds(): Flow<PagingData<Breed>>
    fun searchBreeds(query: String): Flow<PagingData<Breed>>

    suspend fun getBreed(id: Int): Breed?
}