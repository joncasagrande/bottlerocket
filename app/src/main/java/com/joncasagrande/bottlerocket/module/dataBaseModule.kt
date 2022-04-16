package com.joncasagrande.bottlerocket.module

import com.joncasagrande.bottlerocket.dao.BottleRocketDB
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        BottleRocketDB.INSTANCE
    }
    single { get< BottleRocketDB>().storeDao() }
}