package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.repositories.BreedRepository
import javax.inject.Inject

class GetBreedDetailUseCase @Inject constructor(private val breedRepository: BreedRepository) {

    suspend operator fun invoke(breedId: Int) = breedRepository.getBreed(id = breedId)
}