package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.dao.BottleRocketDB
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import com.joncasagrande.bottlerocket.web.repository.StoreAPIImpl
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.concurrent.thread

class StoreRepo(val storeCallback: StoreCallback ) : KoinComponent {


    fun loadStoreFromAPI(){
        val storeApi : StoreAPI  by inject()
        val schedulerProviderImpl  = SchedulerProviderImpl()
        val storeCallback = object : StoreCallback{
            override fun onSuccess(stores: List<Store>) {
                insertListStore(stores)
                storeCallback.onSuccess(stores)
            }

            override fun onError(message: String) {
                storeCallback.onError(message)
                loadStoreFromDB()
            }
        }


        val storeImpl = StoreAPIImpl(storeCallback,storeApi,schedulerProviderImpl)
        storeImpl.getStore()
    }

    private fun insertListStore(stores : List<Store>){
        thread {
            val storeDB = BottleRocketDB.INSTANCE!!.storeDao()
            storeDB.insertStoreList(stores)
        }
    }

    fun loadStoreFromDB(){
        thread {
            val storeDB = BottleRocketDB.INSTANCE!!.storeDao()

            val stores = storeDB.getStores()
            storeCallback.onSuccess(stores)
        }

    }
}