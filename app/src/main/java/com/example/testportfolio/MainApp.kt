package com.example.testportfolio

import android.app.Application
import androidx.work.Configuration
import com.example.testportfolio.data.database.AppDatabase
import com.example.testportfolio.data.mapper.CoinMapper
import com.example.testportfolio.data.network.ApiFactory
import com.example.testportfolio.data.workers.RefreshDataWorkerFactory
import com.example.testportfolio.di.DaggerApplicationComponent

class MainApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }
}