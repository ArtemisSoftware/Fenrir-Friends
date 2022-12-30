package com.artemissoftware.domain.models.data

data class DataResponse<T>(
    val data: T? = null,
    val error: DataError = DataError()
)