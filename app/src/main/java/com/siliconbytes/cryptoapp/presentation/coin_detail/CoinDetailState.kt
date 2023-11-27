package com.siliconbytes.cryptoapp.presentation.coin_detail

import com.siliconbytes.cryptoapp.domain.model.Coin
import com.siliconbytes.cryptoapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
