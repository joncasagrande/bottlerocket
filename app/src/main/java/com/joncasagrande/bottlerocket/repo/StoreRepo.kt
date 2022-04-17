package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.dao.StoreDao
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.network.repository.StoreAPIImpl
import org.koin.core.KoinComponent
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