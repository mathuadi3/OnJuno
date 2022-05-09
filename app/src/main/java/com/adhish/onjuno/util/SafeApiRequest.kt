package com.adhish.onjuno.util

import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): Result<T> {
        return try {
            val c = call.invoke()
            if (c.isSuccessful && c.body() != null)
                Result.Success(c.body()!!)
            else
                Result.Error(code = c.raw().code(), message = c.raw().message())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(code = 0, message = "Network Error")
        }

    }

}