package com.artemissoftware.data.pagination

import androidx.paging.PagingSource.*
import com.artemissoftware.data.remote.DogApi
import com.artemissoftware.data.remote.FakeApiData
import com.artemissoftware.data.remote.FakeDogApi
import com.artemissoftware.data.remote.dto.BreedDto
import com.artemissoftware.data.remote.dto.HeightDto
import com.artemissoftware.data.remote.dto.ImageDto
import com.artemissoftware.data.remote.dto.WeightDto
import com.artemissoftware.data.remote.source.DogApiSource
import com.artemissoftware.data.remote.source.DogApiSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runBlockingTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BreedSearchPagingSourceTest {


    private lateinit var dogApi: DogApi
    private lateinit var breeds: List<BreedDto>

    private lateinit var dogApiSource: DogApiSource

    @Before
    fun setup() {
        dogApi = FakeDogApi()
        breeds = FakeApiData.getBreedDto()

        dogApiSource =  DogApiSourceImpl(dogApi)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Search api with existing breed name, expect single breed result, assert LoadResult_Page`() =
        runBlockingTest {

            val breedSearchPagingSource = BreedSearchPagingSource(dogApi = dogApiSource, query = "Saint")

            assertEquals(
                expected = LoadResult.Page(
                    data = listOf(breeds[1]),
                    prevKey = null,
                    nextKey = 2
                ),
                actual = breedSearchPagingSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with existing breed name, expect multiple breeds result, assert LoadResult_Page`() =
        runBlockingTest {

            val breedSearchPagingSource = BreedSearchPagingSource(dogApi = dogApiSource, query = "s")

            assertEquals<LoadResult<Int, BreedDto>>(
                expected = LoadResult.Page(
                    data = FakeApiData.getBreedDto(),
                    prevKey = null,
                    nextKey = 2
                ),
                actual = breedSearchPagingSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }


    @Test
    fun `Search api with non existing breed name, expect no result, assert LoadResult_Page`() =
        runBlockingTest {

            val breedSearchPagingSource = BreedSearchPagingSource(dogApi = dogApiSource, query = "xdfgsgh")

            assertEquals<LoadResult<Int, BreedDto>>(
                expected = LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = 2
                ),
                actual = breedSearchPagingSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty breed name, assert breeds list and LoadResult_Page`() =
        runBlockingTest {

            val breedSearchPagingSource = BreedSearchPagingSource(dogApi = dogApiSource, query = "")

            val loadResult = breedSearchPagingSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = dogApi.getBreeds(limit = 3, page = 1)

            assertTrue { result.isNotEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }

}