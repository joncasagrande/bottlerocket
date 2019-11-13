package com.joncasagrande.bottlerocket.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joncasagrande.bottlerocket.BottleRocketApplication
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.repo.StoreCallback
import com.joncasagrande.bottlerocket.repo.StoreRepo

class MainActivityViewModel: ViewModel() {


    val listStore : MutableLiveData<List<Store>> = MutableLiveData()
    val errorMessage : MutableLiveData<String> = MutableLiveData()

    val storeRepo : StoreRepo

    init {
        storeRepo = StoreRepo(object : StoreCallback {
            override fun onSuccess(stores: List<Store>) {
                listStore.postValue(stores)
            }

            override fun onError(message: String) {
                errorMessage.setValue(message)
            }

        })
    }

    fun loadStore(context: Context){
        if(BottleRocketApplication.hasConnection(context)){
            storeRepo.loadStoreFromAPI()
        }else{
            storeRepo.loadStoreFromDB()
            errorMessage.setValue(context.getString(R.string.no_conection))
        }
    }
}