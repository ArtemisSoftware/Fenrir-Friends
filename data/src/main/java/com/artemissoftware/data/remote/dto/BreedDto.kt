package com.artemissoftware.data.remote.dto


import com.google.gson.annotations.SerializedName

data class BreedDto(
    @SerializedName("bred_for")
    val bredFor: String,
    @SerializedName("breed_group")
    val breedGroup: String,
    @SerializedName("height")
    val heightDto: HeightDto,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val imageDto: ImageDto,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("reference_image_id")
    val referenceImageId: String,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("weight")
    val weightDto: WeightDto
)