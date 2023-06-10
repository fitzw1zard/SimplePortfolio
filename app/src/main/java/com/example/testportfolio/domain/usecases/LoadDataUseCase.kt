package com.example.testportfolio.domain.usecases

import com.example.testportfolio.domain.repository.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.loadData()
}