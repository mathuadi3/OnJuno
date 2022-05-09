package com.adhish.onjuno.model


import com.google.gson.annotations.SerializedName
import java.util.*

data class CryptoPrice(
    @SerializedName("current_price_in_usd")
    val currentPriceInUsd: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("title")
    val title: String,
    val id: String = UUID.randomUUID().toString()

)