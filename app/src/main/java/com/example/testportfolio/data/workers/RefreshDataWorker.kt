package com.example.testportfolio.data.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.testportfolio.data.database.AppDatabase
import com.example.testportfolio.data.database.CoinInfoDao
import com.example.testportfolio.data.mapper.CoinMapper
import com.example.testportfolio.data.network.ApiFactory
import com.example.testportfolio.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker(
    context: Context,
    params: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, params) {

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
        const val UPDATE_TIME: Long = 60_000

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

    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService,
        private val mapper: CoinMapper
    ): ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
           return RefreshDataWorker(
               context,
               workerParameters,
               coinInfoDao,
               apiService,
               mapper
           )
        }
    }
}