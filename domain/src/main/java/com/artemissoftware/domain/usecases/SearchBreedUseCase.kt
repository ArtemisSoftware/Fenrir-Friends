package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.repositories.BreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchBreedUseCase @Inject constructor(private val breedRepository: BreedRepository) {

    operator fun invoke(query: String): Flow<Resource<List<Breed>>> = flow {

        emit(Resource.Loading())

        val result = breedRepository.searchBreed(query = query)

        result.data?.let { breeds->
            emit(Resource.Success(data = breeds))
        } ?: kotlin.run {
            emit(Resource.Error(message = result.error.message))
        }

    }

}