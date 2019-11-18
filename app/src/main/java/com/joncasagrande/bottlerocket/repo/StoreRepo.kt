package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.dao.StoreDao
import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import com.joncasagrande.bottlerocket.web.repository.StoreAPIImpl
import org.koin.core.KoinComponent
import kotlin.concurrent.thread

class StoreRepo(val storeCallback: StoreCallback ) : KoinComponent {

    fun loadStoreFromAPI(storeCallback: StoreCallback,storeApi : StoreAPI,schedulerProviderImpl : SchedulerProviderImpl){



        val storeImpl = StoreAPIImpl(storeCallback,storeApi,schedulerProviderImpl)
        storeImpl.getStore()
    }




    fun loadStoreFromDB(storeDB : StoreDao){
        thread {
            val stores = storeDB.getStores()
            storeCallback.onSuccess(stores)
        }

    }
}