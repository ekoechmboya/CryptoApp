package com.siliconbytes.cryptoapp.domain.use_case.get_coin

import com.siliconbytes.cryptoapp.common.Resource
import com.siliconbytes.cryptoapp.data.remote.dto.toCoin
import com.siliconbytes.cryptoapp.data.remote.dto.toCoinDetail
import com.siliconbytes.cryptoapp.domain.model.Coin
import com.siliconbytes.cryptoapp.domain.model.CoinDetail
import com.siliconbytes.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
    } catch (e: HttpException){
        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }  catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server, check your internet connection"))
        } }
}