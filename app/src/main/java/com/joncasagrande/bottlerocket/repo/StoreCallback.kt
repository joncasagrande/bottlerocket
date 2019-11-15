package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.model.Store


interface StoreCallback  {
    fun onSuccess(stores: List<Store>)
    fun onError(message: String)
}