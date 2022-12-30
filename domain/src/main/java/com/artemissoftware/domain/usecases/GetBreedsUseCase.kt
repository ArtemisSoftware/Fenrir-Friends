package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.repositories.BreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(private val breedRepository: BreedRepository) {

    operator fun invoke(limit: Int, page: Int): Flow<Resource<List<Breed>>> = flow {

        emit(Resource.Loading())

        val result = breedRepository.getBreeds(limit = limit, page = page)

        result.data?.let { breeds->
            emit(Resource.Success(data = breeds))
        } ?: kotlin.run {
            emit(Resource.Error(message = result.error.message))
        }

    }

}