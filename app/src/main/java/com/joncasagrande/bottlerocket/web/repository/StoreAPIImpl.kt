package com.joncasagrande.bottlerocket.web.repository

import android.util.Log
import com.joncasagrande.bottlerocket.web.BaseAPI
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import com.joncasagrande.bottlerocket.model.StoreRest
import com.joncasagrande.bottlerocket.repo.StoreCallback
import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent

class StoreAPIImpl(val callback: StoreCallback, val storeAPI: StoreAPI,
                   val schedulerProviderImpl: SchedulerProviderImpl) : KoinComponent {
    private var disposable: Disposable? = null

    fun getStore() {
        val postLogin = storeAPI.getStore()
        postLogin.subscribeOn(schedulerProviderImpl.newThread())
            .observeOn(schedulerProviderImpl.ui())
            .subscribe(object : SingleObserver<StoreRest> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(stores: StoreRest) {
                    Log.d(StoreAPI::class.simpleName, "Success called")
                    callback.onSuccess(stores.stores)
                    if(disposable != null && disposable!!.isDisposed){
                        disposable!!.dispose()
                    }

                }

                override fun onError(throwable: Throwable) {
                    Log.d(StoreAPI::class.simpleName, "Error calling address API.", throwable)
                    callback.onError(BaseAPI.getErrorMessage(throwable))
                    if(disposable != null && disposable!!.isDisposed){
                        disposable!!.dispose()
                    }
                }
            })
    }
}