package com.artemissoftware.data.remote

import com.artemissoftware.data.remote.dto.BreedDto
import com.artemissoftware.data.remote.dto.HeightDto
import com.artemissoftware.data.remote.dto.ImageDto
import com.artemissoftware.data.remote.dto.WeightDto

object FakeApiData {

    fun getBreedDto() : List<BreedDto> {

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

        val breedDto2 = BreedDto(
            bredFor = "Small rodent hunting, lapdog",
            breedGroup = "Toy",
            heightDto = heightDto,
            id = 1,
            imageDto = imageDto,
            lifeSpan = "10 - 12 years",
            name = "Saint Bernard",
            origin = "Germany, France",
            referenceImageId = "BJa4kxc4X",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
            weightDto = weightDto
        )

        return listOf(breedDto, breedDto2)
    }
}