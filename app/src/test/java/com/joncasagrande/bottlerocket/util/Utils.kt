package com.joncasagrande.bottlerocket.util

import com.joncasagrande.bottlerocket.model.Store
import org.mockito.Mockito

object Utils {

    fun getListStore() : List<Store>{
        val store= Mockito.mock(Store::class.java)
        val store1= Mockito.mock(Store::class.java)
        val store2= Mockito.mock(Store::class.java)
        return mutableListOf(store,store1,store2)
    }
}