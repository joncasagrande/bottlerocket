package com.joncasagrande.bottlerocket.dao

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.joncasagrande.bottlerocket.model.Store
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class StoreDaoTest {

    lateinit var context : Context
    protected lateinit var db: BottleRocketDB
    lateinit var storeDao : StoreDao
    val store = Store(1,"address","city","Store","10101","logo","123456789","10/12/1111",0.0,0.0)
    val store1 = Store(2,"address1","city1","Store1","101011","logo1","1234567891","10/12/11111",1.0,1.0)

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, BottleRocketDB::class.java).build()
        storeDao = db.storeDao()
    }

    @Test
    fun getStores(){
        storeDao.insertStoreList(listOf<Store>(store,store1))
        val stores = storeDao.getStores()
        assertNotNull(stores)
        assertEquals(stores[0],store)
        assertEquals(stores[1],store1)
    }

    @Test
    fun insertStore(){
        storeDao.insert(store)
        val stores = storeDao.getStores()
        assertNotNull(stores)
        assertEquals(stores[0],store)
    }

    @Test
    fun deleteAll(){
        storeDao.insertStoreList(listOf<Store>(store,store1))
        storeDao.deleteAll()
        val stores = storeDao.getStores()

        assertTrue(stores.isEmpty())

    }


    @After
    fun tearDown(){
        db.clearAllTables()
    }
}