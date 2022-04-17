package com.joncasagrande.bottlerocket.ui.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.databinding.ItemStoreBinding
import com.joncasagrande.bottlerocket.model.Store

class StoreViewHolder(itemView: View, val onClick: (input: Store) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemStoreBinding.bind(itemView)

    fun bind(item: Store) = with(binding) {
        nameTV.text = item.name
        addressTV.text = itemView.context.getString(R.string.address,item.address)
        cityTv.text =  item.city
        phoneTV.text = itemView.context.getString(R.string.phone,item.phone)
        Glide.with(itemView.context).load(item.logo).fitCenter().into(imageView)
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }
}