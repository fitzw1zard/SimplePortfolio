package com.example.testportfolio.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.testportfolio.data.database.CoinInfoDao
import com.example.testportfolio.data.mapper.CoinMapper
import com.example.testportfolio.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorkerFactory @Inject constructor(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return RefreshDataWorker(
            appContext,
            workerParameters,
            coinInfoDao, apiService, mapper
        )
    }
}