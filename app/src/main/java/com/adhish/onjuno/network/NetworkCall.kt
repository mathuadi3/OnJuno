package com.adhish.onjuno.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST

interface NetworkCall {

    @POST("/home")
    suspend fun getValuesState(): Response<ResponseBody>

    @POST("/empty-home")
    suspend fun getEmptyState(): Response<ResponseBody>

}