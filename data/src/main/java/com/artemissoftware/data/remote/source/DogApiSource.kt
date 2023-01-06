package com.artemissoftware.data.remote.source

import androidx.paging.PagingData
import com.artemissoftware.data.dabase.entities.BreedEntity
import com.artemissoftware.data.remote.dto.BreedDto
import com.artemissoftware.domain.models.Breed
import kotlinx.coroutines.flow.Flow

interface DogApiSource {
    suspend fun getBreeds(limit: Int, page: Int): List<BreedDto>
}