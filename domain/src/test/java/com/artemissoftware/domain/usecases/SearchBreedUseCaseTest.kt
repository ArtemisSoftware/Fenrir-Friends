package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.BaseUseCaseTest
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.models.data.DataError
import com.artemissoftware.domain.models.data.DataResponse
import com.artemissoftware.domain.repositories.BreedRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SearchBreedUseCaseTest: BaseUseCaseTest() {

    private lateinit var searchBreedUseCase: SearchBreedUseCase

    private lateinit var breedRepository: BreedRepository

    @Before
    fun setUp() {
        breedRepository = mock()
        searchBreedUseCase = SearchBreedUseCase(breedRepository)
    }

    @Test
    fun `search breeds return success with result`() = runBlockingTest {

        val query = "Affen"

        val breeds = listOf(
            Breed(
                id = 1 ,
                name = "Affenpinscher",
                url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
            )
        )

        whenever(breedRepository.searchBreed(query = query)).thenReturn(
            DataResponse(data = breeds)
        )

        val emissions = searchBreedUseCase(query = query).toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Success)

        verify(breedRepository, times(1)).searchBreed(query = query)
    }


    @Test
    fun `search breeds return failure`() = runBlockingTest {

        val query = "Affen"

        whenever(breedRepository.searchBreed(query = query)).thenReturn(
            DataResponse(error = DataError(message = "Network error"))
        )

        val emissions = searchBreedUseCase(query = query).toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Error)

        verify(breedRepository, times(1)).searchBreed(query = query)
    }

}