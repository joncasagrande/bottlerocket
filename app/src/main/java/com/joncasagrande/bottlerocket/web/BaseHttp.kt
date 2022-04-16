package com.joncasagrande.bottlerocket.web

import com.joncasagrande.bottlerocket.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

class BaseHttp(private val dir: File? = null) {
    var httpClient: OkHttpClient? = null

    private val logginInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    init {
        if (httpClient == null)
            httpClient = createHttpClient()
    }

    private fun createHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient().newBuilder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(logginInterceptor)
        if (dir != null) {
            httpClientBuilder.cache(createCache())
        }
        return httpClientBuilder.build()
    }

    private fun createCache(): Cache {
        val size = 10 * 1024 * 1024 //10mb
        return Cache(dir, size.toLong())
    }
}
