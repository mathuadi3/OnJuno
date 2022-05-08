package com.adhish.onjuno.network

import com.adhish.onjuno.model.ResponseModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST

interface NetworkCall {

    @POST("/home")
    suspend fun getValuesState(): Response<ResponseModel>

    @POST("/empty-home")
    suspend fun getEmptyState(): Response<ResponseModel>

}