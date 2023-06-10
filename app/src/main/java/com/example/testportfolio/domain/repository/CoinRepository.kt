package com.example.testportfolio.domain.repository

import androidx.lifecycle.LiveData
import com.example.testportfolio.domain.entity.CoinInfo

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>

    fun loadData()

}