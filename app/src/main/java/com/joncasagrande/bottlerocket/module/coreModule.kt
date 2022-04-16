package com.jonathan.hostelbedcart.module

import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.web.BaseAPI
import com.joncasagrande.bottlerocket.web.BaseHttp
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import com.joncasagrande.bottlerocket.web.repository.StoreAPIImpl
import org.koin.dsl.module

// just declare it
val CoreModule = module {
    // provided web components
    single { BaseHttp() }
    single { BaseAPI(get(), BaseAPI.serverAddres) }
    single { BaseAPI(get(), BaseAPI.serverAddres).createStoreAPI(StoreAPI::class.java) }

    // Repos
    single { StoreRepo(get(), get()) }

    //APIImpl
    single { StoreAPIImpl(get()) }

}
