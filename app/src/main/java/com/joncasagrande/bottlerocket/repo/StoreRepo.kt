package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.dao.StoreDao
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import com.joncasagrande.bottlerocket.web.repository.StoreAPIImpl
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.concurrent.thread

class StoreRepo(val storeImpl : StoreAPIImpl,val storeDB : StoreDao ) : KoinComponent {

    fun loadStoreFromAPI()= storeImpl.getStore()

    fun loadStoreFromDB()= storeDB.getStores()

    fun saveStores(stores: List<Store>){
        thread {
            storeDB.insertStoreList(stores)
        }
    }
}