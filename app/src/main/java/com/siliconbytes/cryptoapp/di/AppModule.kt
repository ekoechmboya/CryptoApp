package com.siliconbytes.cryptoapp.di

import android.content.Context
import com.siliconbytes.cryptoapp.common.Constants
import com.siliconbytes.cryptoapp.data.remote.CoinPaprikaApi
import com.siliconbytes.cryptoapp.data.repository.CoinRepositoryImpl
import com.siliconbytes.cryptoapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun providePaprikaApi(): CoinPaprikaApi {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(CoinPaprikaApi::class.java)
//    }
//    @Provides
//    @Singleton
//    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
//        return CoinRepositoryImpl(api)
//    }
//}

interface AppModule {
    val coinPaprikaApi: CoinPaprikaApi
    val coinRepository: CoinRepository

}

class AppModuleImpl(
    private val appContext: Context
): AppModule{
    override val coinPaprikaApi: CoinPaprikaApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    override val coinRepository: CoinRepository by lazy {
        CoinRepositoryImpl(coinPaprikaApi)
    }
}