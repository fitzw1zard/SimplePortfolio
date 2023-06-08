package com.example.testportfolio.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testportfolio.data.network.ApiFactory.BASE_IMAGE_URL
import com.example.testportfolio.utils.convertTimestampToTime
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: Long?,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val volumeDayTo: Double?,
    val imageUrl: String?
)