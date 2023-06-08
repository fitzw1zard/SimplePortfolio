package com.example.testportfolio.data.mapper

import com.example.testportfolio.data.database.CoinInfoDbModel
import com.example.testportfolio.data.network.model.CoinInfoDto
import com.example.testportfolio.data.network.model.CoinInfoJsonContainerDto
import com.example.testportfolio.data.network.model.CoinNamesDto
import com.example.testportfolio.domain.entity.CoinInfo
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        highDay = dto.highDay,
        lowDay = dto.lowDay,
        lastMarket = dto.lastMarket,
        volumeDayTo = dto.volumeDayTo,
        imageUrl = dto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesDto: CoinNamesDto): String =
        namesDto.names?.map {
            it.coinName?.name
        }?.joinToString(",").orEmpty()

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastUpdate = dbModel.lastUpdate,
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        lastMarket = dbModel.lastMarket,
        volumeDayTo = dbModel.volumeDayTo,
        imageUrl = dbModel.imageUrl
    )

    fun mapDbModelListToEntityList(dbModelList: List<CoinInfoDbModel>) =
        dbModelList.map {
            mapDbModelToEntity(it)
        }


}
