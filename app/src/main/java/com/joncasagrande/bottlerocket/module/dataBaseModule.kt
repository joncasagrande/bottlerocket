package com.joncasagrande.bottlerocket.module

import androidx.room.Room
import com.joncasagrande.bottlerocket.dao.BottleRocketDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        val build = Room.databaseBuilder(
            androidApplication().applicationContext,
            BottleRocketDB::class.java, "bottle"
        ).build()
        build
    }
    single { get<BottleRocketDB>().storeDao() }
}