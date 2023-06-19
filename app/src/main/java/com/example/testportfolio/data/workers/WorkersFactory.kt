package com.example.testportfolio.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.testportfolio.data.database.CoinInfoDao
import com.example.testportfolio.data.mapper.CoinMapper
import com.example.testportfolio.data.network.ApiService
import javax.inject.Inject
import javax.inject.Provider

class WorkersFactory @Inject constructor(
    private val workersProviders:
    @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            RefreshDataWorker::class.qualifiedName -> {
                val childWorkerFactory =
                    workersProviders[RefreshDataWorker::class.java]?.get()
                return childWorkerFactory?.create(appContext, workerParameters)
            }
            else ->
                null
        }
    }
}