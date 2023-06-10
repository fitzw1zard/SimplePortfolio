package com.example.testportfolio.data.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.testportfolio.data.database.AppDatabase
import com.example.testportfolio.data.mapper.CoinMapper
import com.example.testportfolio.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val coinInfoDao = AppDatabase.getInstance(context).coinInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(REQUEST_LIMIT)
                val fsyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fsyms = fsyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (_: Exception) {
            }
            delay(UPDATE_TIME)
        }
    }

    companion object {

        const val NAME = "RefreshDataWorker"
        const val REQUEST_LIMIT = 100
        const val UPDATE_TIME: Long = 10_000

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>()
                .apply {
                setConstraints(makeConstraints())
            }
                .build()
        }

        private fun makeConstraints() = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()
    }
}