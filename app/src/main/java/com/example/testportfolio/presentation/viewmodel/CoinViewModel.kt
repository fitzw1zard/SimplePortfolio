package com.example.testportfolio.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testportfolio.data.repository.CoinRepositoryImpl
import com.example.testportfolio.domain.usecases.GetCoinInfoListUseCase
import com.example.testportfolio.domain.usecases.GetCoinInfoUseCase
import com.example.testportfolio.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch


class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}