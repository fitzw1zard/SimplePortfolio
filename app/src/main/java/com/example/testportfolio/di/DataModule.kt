package com.example.testportfolio.di

import android.app.Application
import com.example.testportfolio.data.database.AppDatabase
import com.example.testportfolio.data.database.CoinInfoDao
import com.example.testportfolio.data.network.ApiFactory
import com.example.testportfolio.data.network.ApiService
import com.example.testportfolio.data.repository.CoinRepositoryImpl
import com.example.testportfolio.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinInfoDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}