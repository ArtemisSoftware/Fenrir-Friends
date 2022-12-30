package com.artemissoftware.data.mappers

import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.domain.models.data.DataError
import org.junit.Assert
import org.junit.Test

class DataErrorMapperTest {

    @Test
    fun `map FenrisFriendsNetworkException to DataError`() {

        val fenrisFriendsNetworkException = FenrisFriendsNetworkException(
            code = 400,
            message = "Data unavailable to be fetched"
        )

        val dataError = DataError(
            code = 400,
            message = "Data unavailable to be fetched"
        )

        Assert.assertEquals(dataError, fenrisFriendsNetworkException.toDataError())
    }
}