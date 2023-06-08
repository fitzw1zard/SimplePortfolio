package com.example.testportfolio.data.network

import com.example.testportfolio.data.model.CoinInfoListOfData
import com.example.testportfolio.data.model.CoinPriceInfoRawData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 50,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_FROM_SYMBOLS) fsyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tsyms: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"

        private const val CURRENCY = "USD"
        private const val API_KEY = "9a17a761bd231f80531025d3592eea6e70741aac85e8450a1b1c3def7d97cff9"

    }
}