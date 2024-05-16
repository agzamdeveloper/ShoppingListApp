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
        val layout = when(viewType){
            VIEW_ENABLED -> layout.shop_item_enabled
            VIEW_DISABLED -> layout.shop_item_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]
        holder.textViewName.text = shopItem.name
        holder.textViewCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = shopItemList[position]
        return if (shopItem.enabled){
            VIEW_ENABLED
        } else {
            VIEW_DISABLED
        }
    }

    class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(id.textViewName)
        val textViewCount = view.findViewById<TextView>(id.textViewCount)
    }

    companion object{
        const val VIEW_ENABLED = 0
        const val VIEW_DISABLED = 1

        const val MAX_POOL_SIZE = 15
    }
}