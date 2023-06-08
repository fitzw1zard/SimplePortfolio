package com.example.testportfolio.domain.usecases

import com.example.testportfolio.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(fromSymbol: String) = coinRepository.getCoinInfo(fromSymbol)
}