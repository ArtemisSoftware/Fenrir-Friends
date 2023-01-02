package com.artemissoftware.data.remote

import com.artemissoftware.data.errors.FenrisFriendsNetworkException
import com.artemissoftware.data.errors.NetworkErrors
import com.artemissoftware.data.remote.dto.DogApiErrorDto
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.UnknownHostException

object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try {

            callFunction.invoke()

        } catch (ex: Exception) {

            when (ex) {
                is HttpException -> {

                    convertErrorBody(ex)?.let { error ->
                        throw FenrisFriendsNetworkException(code = ex.code(), message = error.message)
                    } ?: run {
                        throw ex
                    }

                }
                is UnknownHostException ->{
                    throw FenrisFriendsNetworkException(code = NetworkErrors.UNKNOWN_HOST.first, message = ex.message)
                }
                else -> throw FenrisFriendsNetworkException(code = NetworkErrors.GENERIC_API_ERROR.first, message = NetworkErrors.GENERIC_API_ERROR.second)
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
}