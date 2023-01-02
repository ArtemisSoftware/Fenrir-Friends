package com.artemissoftware.data.errors

data class FenrisFriendsNetworkException(
    val code: Int,
    override val message: String? = "Network error"): RuntimeException()
