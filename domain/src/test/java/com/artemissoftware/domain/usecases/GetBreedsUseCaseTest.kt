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

class GetBreedsUseCaseTest: BaseUseCaseTest() {

    private lateinit var getBreedsUseCase: GetBreedsUseCase

    private lateinit var breedRepository: BreedRepository

    @Before
    fun setUp() {
        breedRepository = mock()
        getBreedsUseCase = GetBreedsUseCase(breedRepository)
    }

    @Test
    fun `get breeds return success`() = runBlockingTest {

        val breeds = listOf(
            Breed(
                id = 1 ,
                name = "Affenpinscher",
                url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
            )
        )

        whenever(breedRepository.getBreeds(limit = 1, page = 1)).thenReturn(
            DataResponse(data = breeds)
        )

        val emissions = getBreedsUseCase(limit = 1, page = 1).toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Success)

        verify(breedRepository, times(1)).getBreeds(limit = 1, page = 1)
    }


    @Test
    fun `get breeds return failure`() = runBlockingTest {

        whenever(breedRepository.getBreeds(limit = 1, page = 1)).thenReturn(
            DataResponse(error = DataError(message = "No breeds available"))
        )

        val emissions = getBreedsUseCase(limit = 1, page = 1).toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Error)

        verify(breedRepository, times(1)).getBreeds(limit = 1, page = 1)
    }
}
