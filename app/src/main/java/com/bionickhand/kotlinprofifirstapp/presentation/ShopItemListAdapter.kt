package com.bionickhand.kotlinprofifirstapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bionickhand.kotlinprofifirstapp.R.*
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem

class ShopItemListAdapter:
    ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType){
            VIEW_ENABLED -> layout.shop_item_enabled
            VIEW_DISABLED -> layout.shop_item_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.textViewName.text = shopItem.name
        holder.textViewCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.enabled){
            VIEW_ENABLED
        } else {
            VIEW_DISABLED
        }
    }

    companion object{
        const val VIEW_ENABLED = 0
        const val VIEW_DISABLED = 1

        const val MAX_POOL_SIZE = 15
    }
}