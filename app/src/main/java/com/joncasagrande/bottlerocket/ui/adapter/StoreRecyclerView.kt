package com.joncasagrande.bottlerocket.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joncasagrande.bottlerocket.EXTRA_STORE
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.ui.StoreActivity
import kotlinx.android.synthetic.main.item_store.view.*
import java.util.*

class StoreRecyclerView : RecyclerView.Adapter<StoreRecyclerView.StoreViewHolder>() {

    private var data: List<Store> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<Store>) {
        this.data = data
        notifyDataSetChanged()
    }

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("StringFormatInvalid")
        fun bind(item: Store) = with(itemView) {

            addressTV.text = context.getString(R.string.address,item.address)
            cityTv.text =  item.city
            phoneTV.text = context.getString(R.string.phone,item.phone)
            Glide.with(this).load(item.logo).fitCenter().into(imageView)
            setOnClickListener {
                val intent = Intent(itemView.context, StoreActivity::class.java).apply {
                    putExtra(EXTRA_STORE, item)
                }

                startActivity(itemView.context,intent,null)

            }
        }
    }
}