package com.joncasagrande.bottlerocket.network.repository

import com.joncasagrande.bottlerocket.network.api.StoreAPI
import org.koin.core.KoinComponent

class StoreAPIImpl(private val storeAPI: StoreAPI) : KoinComponent {

    fun getStore() = storeAPI.getStore()
}