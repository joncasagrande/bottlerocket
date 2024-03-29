package com.jonathan.hostelbedcart.module

import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.network.BaseAPI
import com.joncasagrande.bottlerocket.network.BaseHttp
import com.joncasagrande.bottlerocket.network.api.StoreAPI
import com.joncasagrande.bottlerocket.network.repository.StoreAPIImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import java.io.File

// just declare it
val CoreModule = module {
    // provided web components

    single {
        File(androidApplication().cacheDir, "http")
    }
    single { BaseHttp(get()) }
    single { BaseAPI(get(), BaseAPI.serverAddres) }
    single { BaseAPI(get(), BaseAPI.serverAddres).createStoreAPI(StoreAPI::class.java) }

    // Repos
    single { StoreRepo(get(), get()) }

    //APIImpl
    single { StoreAPIImpl(get()) }

}
