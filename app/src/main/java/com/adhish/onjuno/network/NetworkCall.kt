package com.adhish.onjuno.network

import com.adhish.onjuno.model.ResponseModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface NetworkCall {

    @GET("home")
    suspend fun getValuesState(): Response<ResponseModel>

    @GET("empty-home")
    suspend fun getEmptyState(): Response<ResponseModel>

}