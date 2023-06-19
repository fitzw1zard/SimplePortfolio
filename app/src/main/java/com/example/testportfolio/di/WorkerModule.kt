package com.example.testportfolio.di

import androidx.work.ListenableWorker
import com.example.testportfolio.data.workers.ChildWorkerFactory
import com.example.testportfolio.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}