package com.joncasagrande.bottlerocket.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joncasagrande.bottlerocket.BottleRocketApplication
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.dao.BottleRocketDB
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.repo.StoreCallback
import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.concurrent.thread

class MainActivityViewModel: ViewModel() , KoinComponent {



    val listStore : MutableLiveData<List<Store>> = MutableLiveData()
    val errorMessage : MutableLiveData<String> = MutableLiveData()

    val storeRepo : StoreRepo
    val  storeCallback : StoreCallback
    init {
        storeCallback =  object : StoreCallback {
            override fun onSuccess(stores: List<Store>) {
                listStore.postValue(stores)
            }

            override fun onError(message: String) {
                errorMessage.setValue(message)
            }

        }
        storeRepo = StoreRepo(storeCallback)
    }

    fun loadStore(context: Context){
        if(BottleRocketApplication.hasConnection(context)){
            val storeCallback = object : StoreCallback{
                override fun onSuccess(stores: List<Store>) {
                    insertListStore(stores)
                    storeCallback.onSuccess(stores)
                }

                override fun onError(message: String) {
                    storeCallback.onError(message)
                    storeRepo.loadStoreFromDB(BottleRocketDB.INSTANCE!!.storeDao())
                }
            }
            val storeApi : StoreAPI  by inject()
            val schedulerProviderImpl : SchedulerProviderImpl by inject()

            storeRepo.loadStoreFromAPI(storeCallback, storeApi,schedulerProviderImpl)
        }else{
            storeRepo.loadStoreFromDB(BottleRocketDB.INSTANCE!!.storeDao())
            errorMessage.setValue(context.getString(R.string.no_conection))
        }
    }

    private fun insertListStore(stores : List<Store>){
        thread {
            val storeDB = BottleRocketDB.INSTANCE!!.storeDao()
            storeDB.insertStoreList(stores)
        }
    }
}