package com.joncasagrande.bottlerocket.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.utils.SchedulerProvider
import org.koin.core.KoinComponent

class MainActivityViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val storeRepo: StoreRepo
) : BaseViewModel(), KoinComponent {

    private val _stores = MutableLiveData<List<Store>>()
    val listStore: LiveData<List<Store>> by lazy {
        _stores
    }

    val errorMessage: MutableLiveData<Boolean> = MutableLiveData()

    fun loadStore() {
        disposables.add(
            storeRepo.loadStoreFromAPI()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({
                    _stores.value = it.stores
                    storeRepo.saveStores(it.stores)
                }, {
                    loadStoreFromDataBase()
                    errorMessage.value = true
                })
        )
    }

    private fun loadStoreFromDataBase(){
        _stores.value = storeRepo.loadStoreFromDB()
    }
}
