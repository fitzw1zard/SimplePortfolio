package com.example.testportfolio.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPricaInfoRawData (
    @SerializedName("RAW")
    @Expose
    val coinPriceInfoJsonObj: JsonObject? = null
        )