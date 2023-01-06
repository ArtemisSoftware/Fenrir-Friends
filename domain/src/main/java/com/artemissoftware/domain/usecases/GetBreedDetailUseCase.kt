package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.repositories.BreedRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreedDetailUseCase @Inject constructor(private val breedRepository: BreedRepository) {

    suspend operator fun invoke(breedId: Int? = null, breed: Breed?= null): Flow<Resource<Breed>> = flow {

        emit(Resource.Loading())

        delay(1500)

        when{

            breed != null ->{
                emit(Resource.Success(breed))
            }
            breedId != null ->{
                val result = breedRepository.getBreed(id = breedId)

                result?.let { breed ->
                    emit(Resource.Success(breed))
                } ?: kotlin.run {
                    emit(Resource.Error(ERROR_UNAVAILABLE_DETAIL_PROVIDED_ID))
                }
            }
            else->{
                emit(Resource.Error(ERROR_UNAVAILABLE_DETAIL_PROVIDED_ID))
            }
        }

    }

    companion object{
        private const val ERROR_UNAVAILABLE_DETAIL_PROVIDED_ID = "Unvailable detail for the provided breed"
    }
}