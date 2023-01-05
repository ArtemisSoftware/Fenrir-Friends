package com.artemissoftware.data.repositories

import androidx.paging.*
import com.artemissoftware.data.BuildConfig
import com.artemissoftware.data.BuildConfig.ITEMS_PER_PAGE
import com.artemissoftware.data.dabase.FenrirDatabase
import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.data.mappers.toBreed
import com.artemissoftware.data.mappers.toDataError
import com.artemissoftware.data.pagination.BreedRemoteMediator
import com.artemissoftware.data.pagination.BreedSearchPagingSource
import com.artemissoftware.data.remote.HandleApi.safeApiCall
import com.artemissoftware.data.remote.source.DogApiSource
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.models.data.DataResponse
import com.artemissoftware.domain.repositories.BreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val dogApiSource: DogApiSource,
    private val fenrirDatabase: FenrirDatabase
) : BreedRepository {

    private val breedDao = fenrirDatabase.breedDao

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
                .filter { breedDto -> breedDto.name.uppercase(Locale.ROOT).contains(query.uppercase(Locale.ROOT)) }

            return DataResponse(data = apiResponse.map { response ->
                response.toBreed()
            })

        } catch (ex: FenrisFriendsNetworkException) {
            DataResponse(error = ex.toDataError())
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getBreeds(): Flow<PagingData<Breed>> {

        val pagingSourceFactory = { breedDao.getBreeds() }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = BreedRemoteMediator(
                dogApi = dogApiSource,
                fenrirDatabase = fenrirDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { breedEntity->
                breedEntity.toBreed()
            }
        }
    }

    override fun searchBreeds(query: String): Flow<PagingData<Breed>> {

        val pagingSourceFactory = { BreedSearchPagingSource(dogApi = dogApiSource, query = query) }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { breedDto->
                breedDto.toBreed()
            }
        }
    }

    override suspend fun getBreed(id: Int): Breed? {
        return breedDao.getBreed(id)?.toBreed()
    }
}