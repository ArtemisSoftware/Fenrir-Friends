package com.artemissoftware.data.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.artemissoftware.data.BuildConfig.CACHE_TIME_OUT
import com.artemissoftware.data.BuildConfig.ITEMS_PER_PAGE
import com.artemissoftware.data.dabase.FenrirDatabase
import com.artemissoftware.data.dabase.entities.BreedEntity
import com.artemissoftware.data.dabase.entities.BreedRemoteKeysEntity
import com.artemissoftware.data.mappers.toEntity
import com.artemissoftware.data.remote.source.DogApiSource
import java.util.*

@OptIn(ExperimentalPagingApi::class)
class BreedRemoteMediator (
    private val dogApi: DogApiSource,
    private val fenrirDatabase: FenrirDatabase
) : RemoteMediator<Int, BreedEntity>() {

    private val breedDao = fenrirDatabase.breedDao
    private val breedRemoteKeysDao = fenrirDatabase.breedRemoteKeysDao

    override suspend fun initialize(): InitializeAction {

        val currentTime = System.currentTimeMillis()
        val lastUpdated = breedRemoteKeysDao.getRemoteKeys()?.lastUpdated ?: 0L
        val cacheTimeoutInMinutes = CACHE_TIME_OUT

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60

        return if (diffInMinutes.toInt() <= cacheTimeoutInMinutes) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            return InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }


    override suspend fun load(loadType: LoadType, state: PagingState<Int, BreedEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = dogApi.getBreeds(limit = ITEMS_PER_PAGE, page = page - 1)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (page == 1) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1

            if (response.isNotEmpty()) {

                fenrirDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        breedDao.deleteBreeds()
                        breedRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val currentTime = System.currentTimeMillis()

                    val keys = response.map { breed ->
                        BreedRemoteKeysEntity(
                            id = breed.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = currentTime
                        )
                    }
                    breedRemoteKeysDao.addAllRemoteKeys(breedRemoteKeys = keys)
                    breedDao.insertBreeds(breeds = response.toEntity())
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, BreedEntity>
    ): BreedRemoteKeysEntity? {

        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                breedRemoteKeysDao.getRemoteKeys(breedId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, BreedEntity>
    ): BreedRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { breed ->
                breedRemoteKeysDao.getRemoteKeys(breedId = breed.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, BreedEntity>
    ): BreedRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { breed ->
                breedRemoteKeysDao.getRemoteKeys(breedId = breed.id)
            }
    }

}