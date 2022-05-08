package com.adhish.onjuno.data

import com.adhish.onjuno.network.NetworkCall
import com.adhish.onjuno.util.SafeApiRequest
import okhttp3.OkHttpClient
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val networkCall: NetworkCall
): SafeApiRequest() {

    suspend fun getEmptyStateData() = apiRequest { networkCall.getEmptyState() }
    suspend fun geValueStateData() = apiRequest { networkCall.getValuesState() }

}