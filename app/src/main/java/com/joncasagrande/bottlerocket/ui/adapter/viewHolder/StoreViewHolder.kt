package com.joncasagrande.bottlerocket.ui.adapter.viewHolder

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joncasagrande.bottlerocket.EXTRA_STORE
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.databinding.ItemStoreBinding
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.ui.StoreActivity

class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemStoreBinding.bind(itemView)

    fun bind(item: Store) = with(binding) {
        nameTV.text = item.name
        addressTV.text = itemView.context.getString(R.string.address,item.address)
        cityTv.text =  item.city
        phoneTV.text = itemView.context.getString(R.string.phone,item.phone)
        Glide.with(itemView.context).load(item.logo).fitCenter().into(imageView)
        binding.root.setOnClickListener {
            val intent = Intent(itemView.context, StoreActivity::class.java).apply {
                putExtra(EXTRA_STORE, item)
            }
            ContextCompat.startActivity(itemView.context, intent, null)
        }
    }
}