package com.example.testportfolio.domain.usecases

import com.example.testportfolio.domain.repository.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {

    suspend operator fun invoke() = repository.loadData()
}