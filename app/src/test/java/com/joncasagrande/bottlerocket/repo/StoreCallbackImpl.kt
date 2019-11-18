package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.model.Store

class StoreCallbackImpl : StoreCallback {
    var stores: List<Store> = emptyList()
        private set
    var erroMessage: String = ""
        private set

    override fun onSuccess(stores: List<Store>) {
        this.stores = stores
    }

    override fun onError(message: String) {
        this.erroMessage = message
    }
}