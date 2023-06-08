package com.example.testportfolio.domain.entity

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: String,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val volumeDayTo: Double?,
    val imageUrl: String
)