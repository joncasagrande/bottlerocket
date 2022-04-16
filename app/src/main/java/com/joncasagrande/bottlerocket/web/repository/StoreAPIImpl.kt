package com.joncasagrande.bottlerocket.web.repository

import android.util.Log
import com.joncasagrande.bottlerocket.web.BaseAPI
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import com.joncasagrande.bottlerocket.model.StoreRest
import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent

class StoreAPIImpl(val storeAPI: StoreAPI) : KoinComponent {

    fun getStore() = storeAPI.getStore()
}