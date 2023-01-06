package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.BaseUseCaseTest
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Breed
import com.artemissoftware.domain.repositories.BreedRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class GetBreedDetailUseCaseTest: BaseUseCaseTest() {

    private lateinit var getBreedDetailUseCase: GetBreedDetailUseCase

    private lateinit var breedRepository: BreedRepository

    @Before
    fun setUp() {
        breedRepository = mock()
        getBreedDetailUseCase = GetBreedDetailUseCase(breedRepository)
    }

    @Test
    fun `get breed from database`() = runBlockingTest {

        val breeds = listOf(
            Breed(
                id = 1 ,
                name = "Affenpinscher",
                url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
            )
        )

        whenever(breedRepository.getBreed(1)).thenReturn(
            breeds[0]
        )

        val emissions = getBreedDetailUseCase(breedId = 1).toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Success)

        verify(breedRepository, times(1)).getBreed(id = 1)
    }


    @Test
    fun `get breed from ui`() = runBlockingTest {

        val breeds = listOf(
            Breed(
                id = 1 ,
                name = "Affenpinscher",
                url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
            )
        )

        val emissions = getBreedDetailUseCase(breed = breeds.first()).toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Success)

        verify(breedRepository, times(0)).getBreed(id = any())
    }

    @Test
    fun `get breed and give preference to ui source`() = runBlockingTest {

        val breeds = listOf(
            Breed(
                id = 1 ,
                name = "Affenpinscher",
                url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
            )
        )

        whenever(breedRepository.getBreed(1)).thenReturn(
            breeds[0]
        )

        val emissions = getBreedDetailUseCase(breed = breeds[0], breedId = 1).toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Success)

        verify(breedRepository, times(0)).getBreed(id = any())
    }


    @Test
    fun `get breeds with no arguments return failure`() = runBlockingTest {

        whenever(breedRepository.getBreed(1)).thenReturn(
            null
        )

        val emissions = getBreedDetailUseCase().toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Error)

        verify(breedRepository, times(0)).getBreed(id = any())
    }

    @Test
    fun `get breeds with inexistent id arguments return failure`() = runBlockingTest {

        whenever(breedRepository.getBreed(-10)).thenReturn(
            null
        )

        val emissions = getBreedDetailUseCase(breedId = -10).toList()

        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Error)

        verify(breedRepository, times(1)).getBreed(id = -10)
    }
}