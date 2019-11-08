package com.joncasagrande.bottlerocket.web

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BaseAPI(val baseHttp : BaseHttp,val serverAddress: String) {
    private val builder: Retrofit.Builder
    internal var okHttpClient: OkHttpClient? = null

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




    private inner class ErrorParser {
        @SerializedName("errors")
        internal var errors: JsonObject? = null

        val error: String
            get() {
                if (errors == null) {
                    return ""
                }
                val error = StringBuilder()
                for ((key, value) in errors!!.entrySet()) {
                    error.append(key).append(" ")
                    error.append(value.asString).append("; ")
                }
                return error.toString()
            }
    }

    companion object {

        val serverAddres: String = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/"
        private val TAG = BaseAPI::class.java.simpleName

        fun getErrorMessage(throwable: Throwable): String {
            if (throwable is HttpException) {
                val body = throwable.response().errorBody()
                val gson = Gson()

                val adapter = gson.getAdapter(ErrorParser::class.java)
                try {
                    val errorParser = adapter.fromJson(body!!.string())
                    return errorParser.error
                } catch (e: Exception) {
                    Log.d(TAG, "Error parsing error message", e)
                }

            }
            return ""
        }
    }
}