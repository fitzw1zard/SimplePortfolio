package com.example.testportfolio.data.network

import com.example.testportfolio.BuildConfig
import com.example.testportfolio.data.network.model.CoinInfoJsonContainerDto
import com.example.testportfolio.data.network.model.CoinNamesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): CoinNamesDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_FROM_SYMBOLS) fsyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tsyms: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): CoinInfoJsonContainerDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"

        private const val CURRENCY = "USD"
        private const val API_KEY = ""

    //        private const val API_KEY = BuildConfig.API_KEY

    }
}