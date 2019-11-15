package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.model.Store

class StoreCallbackImpl : StoreCallback {
    var stores: List<Store> = emptyList()
    var erroMessage: String = ""
    override fun onSuccess(stores: List<Store>) {
        this.stores = stores
    }

    override fun onError(message: String) {
        this.erroMessage = message
    }
}