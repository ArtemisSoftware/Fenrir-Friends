package com.artemissoftware.data.mappers

import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.domain.models.data.DataError

fun FenrisFriendsNetworkException.toDataError() = DataError(
    code =  this.code,
    message = this.message ?: ""
)