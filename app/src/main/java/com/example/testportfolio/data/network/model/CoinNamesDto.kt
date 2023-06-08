package com.example.testportfolio.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesDto(
    @SerializedName("Data")
    @Expose
    val names: List<CoinNameContainerDto>? = null
)
