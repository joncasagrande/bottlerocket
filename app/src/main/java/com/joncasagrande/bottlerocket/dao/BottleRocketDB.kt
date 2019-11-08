package com.joncasagrande.bottlerocket.dao

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import com.joncasagrande.bottlerocket.model.Store



@Database(entities = [Store::class], version = 1)
abstract class BottleRocketDB : RoomDatabase() {
    abstract fun storeDao(): StoreDao

    companion object {
        var INSTANCE: BottleRocketDB? = null

        fun createAppDataBase(context: Context){
            if (INSTANCE == null){
                synchronized(BottleRocketDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, BottleRocketDB::class.java, "bottle").build()
                }
            }
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}