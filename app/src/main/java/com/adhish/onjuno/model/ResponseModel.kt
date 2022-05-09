package com.adhish.onjuno.model


import com.google.gson.annotations.SerializedName
import java.util.*

data class ResponseModel(
    @SerializedName("crypto_balance")
    val cryptoBalance: CryptoBalance,
    @SerializedName("crypto_prices")
    val cryptoPrices: List<CryptoPrice>,
    @SerializedName("your_crypto_holdings")
    val yourCryptoHoldings: List<YourCryptoHolding>,
    @SerializedName("all_transactions")
    val allTransactions: List<AllTransaction>,
    val id: String = UUID.randomUUID().toString()
)