package com.example.testportfolio

import android.app.Application
import androidx.work.Configuration
import com.example.testportfolio.data.workers.WorkersFactory
import com.example.testportfolio.di.DaggerApplicationComponent
import javax.inject.Inject


class MainApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkersFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}