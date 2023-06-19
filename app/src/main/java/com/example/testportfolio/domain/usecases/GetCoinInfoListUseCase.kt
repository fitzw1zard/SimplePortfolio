package com.example.testportfolio.domain.usecases

import com.example.testportfolio.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke() = coinRepository.getCoinInfoList()
}