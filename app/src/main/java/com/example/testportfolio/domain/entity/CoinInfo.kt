package com.example.testportfolio.domain.entity

data class CoinInfo(
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