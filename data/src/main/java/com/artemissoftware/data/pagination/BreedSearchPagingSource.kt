package com.artemissoftware.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.data.BuildConfig.ITEMS_PER_PAGE
import com.artemissoftware.data.remote.dto.BreedDto
import com.artemissoftware.data.remote.source.DogApiSource
import java.util.*

class BreedSearchPagingSource(
    private val dogApi: DogApiSource,
    private val query: String
) : PagingSource<Int, BreedDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BreedDto> {
        val currentPage = params.key ?: 1
        return try {
            val response = dogApi.getBreeds(limit = ITEMS_PER_PAGE, page = currentPage - 1)
            val endOfPaginationReached = response.isEmpty()
            if (response.isNotEmpty()) {

                val searchResult = response.filter { breedDto ->
                    breedDto.name.uppercase(Locale.ROOT).contains(query.uppercase(Locale.ROOT))
                }

                LoadResult.Page(
                    data = searchResult,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BreedDto>): Int? {
        return state.anchorPosition
    }

}