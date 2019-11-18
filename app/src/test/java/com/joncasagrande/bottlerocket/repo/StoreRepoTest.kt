package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.dao.StoreDao
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import com.joncasagrande.bottlerocket.web.api.StoreAPI
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.*
import org.mockito.Mockito.*

class StoreRepoTest {

    /**
     * I did not succed to test this part.
     * Need to rethink and learn about the captor.


    lateinit var storeCallback :StoreCallbackImpl

    @Captor
    var argCaptor: ArgumentCaptor<StoreCallback>? = null


    @Mock
    lateinit var storeRepo : StoreRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        storeCallback = StoreCallbackImpl()

        //storeRepo = StoreRepo(storeCallback)
    }

    @Test
    fun loadStoreFromAPI() {

        var mockApi = mock(StoreAPI::class.java)
        var scheduler = mock(SchedulerProviderImpl::class.java)
        var localStoreCallback =  object : StoreCallback {
            var stores : List<Store>? = null
            var errorMessage : String? = null
            override fun onSuccess(stores: List<Store>) {
               stores
            }

            override fun onError(message: String) {
                errorMessage = message
            }

        }
        `when`(storeRepo.loadStoreFromAPI(localStoreCallback,mockApi,scheduler)).thenReturn(storeCallback.onSuccess(emptyList()))

        assertEquals(storeCallback.stores, emptyList<Store>())
    }

    @Test
    fun loadStoreFromDB() {
        var storeDao = Mockito.mock(StoreDao::class.java)
        verify(storeRepo).loadStoreFromDB(storeDao)
        val storeRepoCallback = argCaptor!!.value
        val data = storeRepoCallback.onSuccess(emptyList<Store>())
        assertEquals(data, emptyList<Store>())

    }
     */
}