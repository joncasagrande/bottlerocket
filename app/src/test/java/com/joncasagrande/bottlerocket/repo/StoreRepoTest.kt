package com.joncasagrande.bottlerocket.repo

import com.joncasagrande.bottlerocket.dao.StoreDao
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.model.StoreRest
import com.joncasagrande.bottlerocket.network.repository.StoreAPIImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class StoreRepoTest {

    /**
     * I did not succed to test this part.
     * Need to rethink and learn about the captor.
     */

    lateinit var storeRepo: StoreRepo

    @Mock
    lateinit var storeImpl: StoreAPIImpl

    @Mock
    lateinit var storeDao: StoreDao

    @Captor
    var argCaptor: ArgumentCaptor<StoreDao>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        storeRepo = StoreRepo(storeImpl, storeDao)
    }

    @Test
    fun loadStoreFromAPI() {
        // given
        val singleReturn = Single.just(StoreRest(emptyList()))
        `when`(storeImpl.getStore()).thenReturn(singleReturn)

        //when
        val single = storeRepo.loadStoreFromAPI()

        //than
        assert(single == singleReturn)
    }

    @Test
    fun loadStoreFromDB() {
        // given
        `when`(storeDao.getStores()).thenReturn(emptyList())

        //when
        val response = storeRepo.loadStoreFromDB()

        //than
        assert(response.isEmpty())
    }

    @Test
    fun saveStoreFromDB() {
        // given
        val store= mock(Store::class.java)
        argCaptor = ArgumentCaptor.forClass(StoreDao::class.java)

        //when
        val response = storeRepo.saveStores(listOf(store))

        //than
        verify(storeDao).insert(any())
        verify(storeDao, times(1)).insert(store);
    }
}