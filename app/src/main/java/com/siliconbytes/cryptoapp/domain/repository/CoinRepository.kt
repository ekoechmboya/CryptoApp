package com.siliconbytes.cryptoapp.domain.repository

import com.siliconbytes.cryptoapp.data.remote.dto.CoinDetailDto
import com.siliconbytes.cryptoapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}