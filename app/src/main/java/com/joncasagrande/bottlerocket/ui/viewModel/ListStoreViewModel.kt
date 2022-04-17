package com.joncasagrande.bottlerocket.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.ui.model.UiState
import com.joncasagrande.bottlerocket.utils.SchedulerProvider
import org.koin.core.KoinComponent

class ListStoreViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val storeRepo: StoreRepo
) : BaseViewModel(), KoinComponent {

    private val _stores = MutableLiveData<UiState<List<Store>>>()
    val listStore: LiveData<UiState<List<Store>>> by lazy {
        _stores
    }

    val errorMessage: MutableLiveData<Boolean> = MutableLiveData()

    fun loadStore() {
        disposables.add(
            storeRepo.loadStoreFromAPI()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .doOnSubscribe {
                    _stores.value = UiState.Loading
                }
                .subscribe({
                    _stores.value = UiState.Display(it.stores)
                    storeRepo.saveStores(it.stores)
                }, {
                    loadStoreFromDataBase()
                    errorMessage.value = true
                })
        )
    }

    private fun loadStoreFromDataBase(){
        _stores.value = UiState.Display(storeRepo.loadStoreFromDB())
    }
}
