package com.artemissoftware.data.errors

data class DogApiNetworkException(
    val code: Int?,
    override val message: String): RuntimeException()
