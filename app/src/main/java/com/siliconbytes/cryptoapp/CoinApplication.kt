package com.siliconbytes.cryptoapp

import android.app.Application
import com.siliconbytes.cryptoapp.di.AppModule
import com.siliconbytes.cryptoapp.di.AppModuleImpl

class CoinApplication : Application(){

    companion object{
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}