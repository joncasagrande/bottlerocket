package com.joncasagrande.bottlerocket.ui.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joncasagrande.bottlerocket.*
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.ui.adapter.viewHolder.StoreViewHolder
import kotlinx.android.synthetic.main.item_store.view.*

class StoreRecyclerView : RecyclerView.Adapter<StoreViewHolder>() {

    private var data: MutableList<Store> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) =
        holder.bind(data[position])

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int, payloads: MutableList<Any>) {
        if(payloads.isEmpty()){
            onBindViewHolder(holder,position)
        }else{
            val bundle: Bundle = payloads[0] as Bundle
            Log.d("StoreRecyclerView", "has bundle ${bundle.size()}")

            if(bundle[NAME] != null){
                holder.itemView.nameTV.text = data[position].name
            }
            if(bundle[ADDRESS] != null){
                holder.itemView.addressTV.text = holder.itemView.context.getString(R.string.address,data[position].address)
            }
            if(bundle[CITY] != null){
                holder.itemView.cityTv.text = data[position].city
            }
            if(bundle[PHONE] != null){
                holder.itemView.phoneTV.text = holder.itemView.context.getString(R.string.phone,data[position].phone)
            }
            if(bundle[PICTURE] != null){
                Glide.with(holder.itemView.context).load(data[position].logo).fitCenter().into(holder.itemView.imageView)
            }
        }
    }

    fun swapData(contacts: List<Store>) {
        val diffCallback = StoreDiffUtils(this.data, contacts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(contacts)
        diffResult.dispatchUpdatesTo(this)
    }

}