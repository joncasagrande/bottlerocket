package com.joncasagrande.bottlerocket.dao

import androidx.room.RoomDatabase
import androidx.room.Database
import com.joncasagrande.bottlerocket.model.Store



@Database(entities = [Store::class], version = 1)
abstract class BottleRocketDB : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}