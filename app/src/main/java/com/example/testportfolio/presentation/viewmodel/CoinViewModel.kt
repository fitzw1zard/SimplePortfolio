package com.example.testportfolio.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.testportfolio.data.repository.CoinRepositoryImpl
import com.example.testportfolio.domain.usecases.GetCoinInfoListUseCase
import com.example.testportfolio.domain.usecases.GetCoinInfoUseCase
import com.example.testportfolio.domain.usecases.LoadDataUseCase
import javax.inject.Inject


class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }

}