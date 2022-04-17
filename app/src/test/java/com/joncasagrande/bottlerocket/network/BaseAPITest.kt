package com.joncasagrande.bottlerocket.network

import com.joncasagrande.bottlerocket.network.api.StoreAPI
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class BaseAPITest {

    lateinit var baseAPI: BaseAPI
    val serverAddress = "http://serverAddressTest"
    @Before
    fun setUp() {
        baseAPI = BaseAPI(BaseHttp(),serverAddress)
    }

    @Test
    fun testServerAddress(){
        assertEquals(baseAPI.serverAddress,serverAddress)
    }


    @Test
    fun createStoreAPI() {
        assertNotNull(baseAPI.createStoreAPI(StoreAPI::class.java))
    }
}