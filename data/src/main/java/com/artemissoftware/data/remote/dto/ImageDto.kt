package com.artemissoftware.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)