package com.artemissoftware.data.mappers

import com.artemissoftware.data.dabase.entities.BreedEntity
import com.artemissoftware.data.remote.dto.BreedDto
import com.artemissoftware.data.remote.dto.HeightDto
import com.artemissoftware.data.remote.dto.ImageDto
import com.artemissoftware.data.remote.dto.WeightDto
import com.artemissoftware.domain.models.Breed
import org.junit.Assert.assertEquals
import org.junit.Test

class DogMapperTest {

    @Test
    fun `map BreedDto to Breed`() {

        val breedDto = getBreedDto()

        val breed = Breed(
            id = 1 ,
            name = "Affenpinscher",
            url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            group = "Toy",
            origin = "Germany, France",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        )

        assertEquals(breed, breedDto.toBreed())
    }

    @Test
    fun `map BreedDto to BreedEntity`() {

        val breedDto = getBreedDto()

        val breedEntity = BreedEntity(
            id = 1 ,
            name = "Affenpinscher",
            url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            group = "Toy",
            origin = "Germany, France",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        )

        assertEquals(breedEntity, breedDto.toEntity())
    }

    @Test
    fun `map BreedDto list to BreedEntity list`() {

        val listBreedDto = listOf(getBreedDto())

        val breedEntity = BreedEntity(
            id = 1 ,
            name = "Affenpinscher",
            url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            group = "Toy",
            origin = "Germany, France",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        )

        val listBreedEntity = listOf(breedEntity)

        assertEquals(listBreedEntity, listBreedDto.toEntity())
    }


    @Test
    fun `map BreedEntity to Breed`() {

        val breedEntity = BreedEntity(
            id = 1 ,
            name = "Affenpinscher",
            url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            group = "Toy",
            origin = "Germany, France",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        )

        val breed = Breed(
            id = 1 ,
            name = "Affenpinscher",
            url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            group = "Toy",
            origin = "Germany, France",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        )

        assertEquals(breed, breedEntity.toBreed())
    }

    private fun getBreedDto() : BreedDto {

        val weightDto = WeightDto(
            imperial = "6 - 13",
            metric = "3 - 6"
        )

        val heightDto = HeightDto(
            imperial = "9 - 11.5",
            metric = "23 - 29"
        )

        val imageDto = ImageDto(
            id = "BJa4kxc4X",
            width = 1600,
            height = 1199,
            url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
        )

        val breedDto = BreedDto(
            bredFor = "Small rodent hunting, lapdog",
            breedGroup = "Toy",
            heightDto = heightDto,
            id = 1,
            imageDto = imageDto,
            lifeSpan = "10 - 12 years",
            name = "Affenpinscher",
            origin = "Germany, France",
            referenceImageId = "BJa4kxc4X",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
            weightDto = weightDto
        )

        return breedDto
    }
}