package com.joncasagrande.bottlerocket

import android.app.Application
import android.content.Context
import com.jonathan.hostelbedcart.module.viewModelModule
import com.jonathan.hostelbedcart.module.webModule
import com.joncasagrande.bottlerocket.dao.BottleRocketDB
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.NetworkInfo


class BottleRocketApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BottleRocketApplication)
            modules(listOf(viewModelModule, webModule))
        }

        BottleRocketDB.createAppDataBase(this)
    }

    companion object{
        fun hasConnection(context : Context): Boolean{
            val cm = getSystemService(context,ConnectivityManager::class.java) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting?: false
        }
    }
}