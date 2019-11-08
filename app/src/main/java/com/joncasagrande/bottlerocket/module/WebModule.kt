package com.jonathan.hostelbedcart.module

import com.joncasagrande.bottlerocket.web.BaseAPI
import com.joncasagrande.bottlerocket.web.BaseHttp
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import org.koin.dsl.module

// just declare it
val webModule = module {
    // provided web components
    single { BaseHttp() }
    single { BaseAPI(get(),BaseAPI.serverAddres) }
    single { BaseAPI(get(),BaseAPI.serverAddres).createStoreAPI(StoreAPI::class.java) }

}
