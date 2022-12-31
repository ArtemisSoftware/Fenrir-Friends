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

    override suspend fun getBreeds(limit: Int, page: Int): DataResponse<List<Breed>> {

        return try {

            val apiResponse = safeApiCall { dogApiSource.getBreeds(limit = limit, page = page) }

            return DataResponse(data = apiResponse.map { response ->
                response.toBreed()
            })

        } catch (ex: FenrisFriendsNetworkException) {
            DataResponse(error = ex.toDataError())
        }
    }

    override suspend fun searchBreed(query: String): DataResponse<List<Breed>> {

        return try {

            val apiResponse = safeApiCall { dogApiSource.getBreeds() }
                .filter { breedDto -> breedDto.name.contains(query) }

            return DataResponse(data = apiResponse.map { response ->
                response.toBreed()
            })

        } catch (ex: FenrisFriendsNetworkException) {
            DataResponse(error = ex.toDataError())
        }
    }
}