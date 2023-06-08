package com.example.testportfolio.domain.usecases

import com.example.testportfolio.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val coinRepository: CoinRepository
) {
    operator fun invoke() = coinRepository.getCoinInfoList()
}