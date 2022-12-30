package com.artemissoftware.data.remote

import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.data.remote.dto.DogApiErrorDto
import com.google.gson.Gson
import retrofit2.HttpException

object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try {

            callFunction.invoke()

        } catch (ex: Exception) {

            when (ex) {
                is HttpException -> {

                    convertErrorBody(ex)?.let { error ->
                        throw FenrisFriendsNetworkException(code = UNKNOWN_API_ERROR_CODE, message = error.message)
                    } ?: run {
                        throw ex
                    }

                }
                else -> throw FenrisFriendsNetworkException(code = GENERIC_API_ERROR_CODE, message = UNKNOWN_ERROR_MESSAGE)
            }
        }
    }


    private fun convertErrorBody(httpException: HttpException): DogApiErrorDto? {
        return try {
            httpException.response()?.errorBody()?.let {
                Gson().fromJson(it.charStream(), DogApiErrorDto::class.java)
            }
        } catch (exception: Exception) {
            null
        }
    }

    private const val UNKNOWN_API_ERROR_CODE = -1
    private const val GENERIC_API_ERROR_CODE = -2
    private const val UNKNOWN_ERROR_MESSAGE = "An error has occurred"
}