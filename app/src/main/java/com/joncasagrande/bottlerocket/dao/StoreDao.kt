package com.joncasagrande.bottlerocket.dao

import androidx.room.OnConflictStrategy
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joncasagrande.bottlerocket.model.Store

@Dao
interface StoreDao {

    @Query("SELECT * from Store")
    fun getStores() : List<Store>

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(store: Store)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStoreList(stores: List<Store>)

    @Query("DELETE FROM Store")
    fun deleteAll()
}