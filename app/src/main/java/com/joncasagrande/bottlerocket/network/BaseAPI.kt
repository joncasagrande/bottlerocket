package com.joncasagrande.bottlerocket.network

import com.joncasagrande.bottlerocket.network.api.StoreAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BaseAPI(private val baseHttp: BaseHttp, val serverAddress: String) {
    private val builder: Retrofit.Builder
    private var okHttpClient: OkHttpClient? = null

    init {
        okHttpClient = baseHttp.httpClient
        builder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }


    fun createStoreAPI(serviceClass: Class<StoreAPI>): StoreAPI {
        builder.baseUrl(serverAddress)

        val retrofit = builder.client(okHttpClient!!).build()
        return retrofit.create(serviceClass)
    }

    companion object {
        val serverAddres: String =
            "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/"
    }
}