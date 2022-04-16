package com.joncasagrande.bottlerocket.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joncasagrande.bottlerocket.BottleRocketApplication
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.utils.SchedulerProvider
import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityViewModel() : BaseViewModel(), KoinComponent {

    private val schedulerProvider: SchedulerProviderImpl by inject()
    private val storeRepo: StoreRepo by inject()

    private val _stores = MutableLiveData<List<Store>>()
    val listStore: LiveData<List<Store>> by lazy {
        _stores
    }

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun loadStore(context: Context) {
        disposables.add(
            storeRepo.loadStoreFromAPI()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({
                    _stores.value = it.stores
                    storeRepo.saveStores(it.stores)
                }, {
                    loadStoreFromDataBase()
                    errorMessage.value = context.getString(R.string.no_conection)
                })
        )
    }

    private fun loadStoreFromDataBase(){
        _stores.value = storeRepo.loadStoreFromDB()
    }
}
