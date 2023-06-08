package com.example.testportfolio.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.testportfolio.data.database.AppDatabase
import com.example.testportfolio.data.mapper.CoinMapper
import com.example.testportfolio.data.network.ApiFactory
import com.example.testportfolio.domain.entity.CoinInfo
import com.example.testportfolio.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        MediatorLiveData<List<CoinInfo>>().apply {
            addSource(coinInfoDao.getPriceList()) {
                value = mapper.mapDbModelListToEntityList(it)
            }
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        MediatorLiveData<CoinInfo>().apply {
            addSource(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
                value = mapper.mapDbModelToEntity(it)
            }
        }

    override suspend fun loadData() {
        while (true) {

            try {
                val topCoins = apiService.getTopCoinsInfo()
                val fsyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fsyms = fsyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (_: Exception) {
            }
            delay(60_000)
        }
    }
}