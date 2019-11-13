package com.joncasagrande.bottlerocket.ui.adapter

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.joncasagrande.bottlerocket.*
import com.joncasagrande.bottlerocket.model.Store

class StoreDiffUtils(val oldList: List<Store>, val newList: List<Store>) : DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newStore = newList[newItemPosition]
        val oldStore = oldList[oldItemPosition]

        return newStore.name === oldStore.name && newStore.city === oldStore.city
                && newStore.phone === oldStore.phone
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle = Bundle()
        val newStore = newList[newItemPosition]
        val oldStore = oldList[oldItemPosition]

        if(newStore.name != oldStore.name){
            bundle.putString(NAME, newStore.name)
        }

        if(newStore.address != oldStore.address){
            bundle.putString(ADDRESS, newStore.name)
        }

        if(newStore.city != oldStore.city){
            bundle.putString(CITY, newStore.city)
        }

        if(newStore.phone != oldStore.phone){
            bundle.putString(PHONE, newStore.phone)
        }

        if((!newStore.logo.isNullOrEmpty() && !oldStore.logo.isNullOrEmpty()) ||
            (newStore.logo.isNullOrEmpty() && oldStore.logo.isNullOrEmpty())){
            bundle.putBoolean(PICTURE, true)
        }

        return bundle
    }
}