package com.artemissoftware.domain.usecases

import androidx.paging.PagingData
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.repositories.BreedRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(private val breedRepository: BreedRepository) {

    operator fun invoke(): Flow<PagingData<Breed>> = breedRepository.getBreeds()

}