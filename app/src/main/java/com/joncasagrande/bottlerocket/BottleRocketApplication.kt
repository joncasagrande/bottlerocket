package com.joncasagrande.bottlerocket

import android.app.Application
import com.joncasagrande.bottlerocket.module.viewModelModule
import com.jonathan.hostelbedcart.module.CoreModule
import com.joncasagrande.bottlerocket.dao.BottleRocketDB
import com.joncasagrande.bottlerocket.module.dataBaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BottleRocketApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BottleRocketApplication)
            modules(listOf(viewModelModule, CoreModule, dataBaseModule))
        }
    }
}