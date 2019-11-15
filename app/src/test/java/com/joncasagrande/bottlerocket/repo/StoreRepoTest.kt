package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.util.Utils
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.*
import org.mockito.Mockito.*

class StoreRepoTest {

    @Mock
    lateinit var storeCallback :StoreCallback

    @Captor
    var argCaptor: ArgumentCaptor<StoreCallback>? = null


    lateinit var storeRepo : StoreRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)


        storeRepo = StoreRepo(argCaptor!!.capture())
    }

    @Test
    fun loadStoreFromAPI() {
        verify(storeRepo).loadStoreFromAPI()
        val storeRepoCallback = argCaptor!!.value
        val data = storeRepoCallback.onSuccess(Utils.getListStore())
        assertEquals(data, emptyList<Store>())
    }

    @Test
    fun loadStoreFromDB() {

        verify(storeRepo).loadStoreFromDB()
        val storeRepoCallback = argCaptor!!.value
        val data = storeRepoCallback.onSuccess(emptyList<Store>())
        assertEquals(data, emptyList<Store>())

    }
}