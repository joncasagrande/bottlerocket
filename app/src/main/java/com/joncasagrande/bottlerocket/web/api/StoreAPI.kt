package com.joncasagrande.bottlerocket.web.api

import com.joncasagrande.bottlerocket.model.StoreRest
import io.reactivex.Single
import retrofit2.http.GET

interface StoreAPI {

    @GET("stores.json")
    fun getStore(): Single<StoreRest>

}