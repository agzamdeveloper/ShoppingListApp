package com.bionickhand.kotlinprofifirstapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.savedstate.R
import androidx.recyclerview.widget.RecyclerView
import com.bionickhand.kotlinprofifirstapp.R.*
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem

class ShopItemListAdapter: RecyclerView.Adapter<ShopItemListAdapter.ShopItemViewHolder>() {
    var shopItemList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout.shop_item_disabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]
        val status = if (shopItem.enabled){
            "active"
        } else {
            "not active"
        }

        holder.view.setOnLongClickListener {
            true
        }
        if (shopItem.enabled){
            holder.textViewName.text = "${shopItem.name} $status"
            holder.textViewCount.text = shopItem.count.toString()
            holder.textViewName.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.holo_red_light))
        }
    }

    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.textViewName.text = ""
        holder.textViewCount.text = ""
        holder.textViewName.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.white))
    }

    class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(id.textViewName)
        val textViewCount = view.findViewById<TextView>(id.textViewCount)
    }
}