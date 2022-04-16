package com.joncasagrande.bottlerocket.web.repository

import com.joncasagrande.bottlerocket.web.api.StoreAPI
import org.koin.core.KoinComponent

class StoreAPIImpl(val storeAPI: StoreAPI) : KoinComponent {

    fun getStore() = storeAPI.getStore()
}