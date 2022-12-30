package com.artemissoftware.data.repositories

import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.data.mappers.toBreed
import com.artemissoftware.data.mappers.toDataError
import com.artemissoftware.data.remote.HandleApi.safeApiCall
import com.artemissoftware.data.remote.source.DogApiSource
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.models.data.DataResponse
import com.artemissoftware.domain.repositories.BreedRepository
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val dogApiSource: DogApiSource
) : BreedRepository {

    override suspend fun getBreeds(): DataResponse<List<Breed>> {

        return try {

            val apiResponse = safeApiCall { dogApiSource.getBreeds() }

            return DataResponse(data = apiResponse.map { response ->
                response.toBreed()
            })

        } catch (ex: FenrisFriendsNetworkException) {
            DataResponse(error = ex.toDataError())
        }
    }
}