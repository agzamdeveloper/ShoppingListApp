package com.bionickhand.kotlinprofifirstapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bionickhand.kotlinprofifirstapp.R
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var llShopItemList: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llShopItemList = findViewById(R.id.ll_shopItem_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopItemList.observe(this){
            showItemList(it)
        }
    }

    private fun showItemList(list: List<ShopItem>){
        llShopItemList.removeAllViews()
        for (shopItem in list){
            val layoutId = if (shopItem.enabled){
                R.layout.shop_item_enabled
            } else {
                R.layout.shop_item_disabled
            }

            val view = LayoutInflater.from(this).inflate(layoutId, llShopItemList, false)
            val textViewName = view.findViewById<TextView>(R.id.textViewName)
            val textViewCount = view.findViewById<TextView>(R.id.textViewCount)
            textViewName.text = shopItem.name
            textViewCount.text = shopItem.count.toString()
            llShopItemList.addView(view)
            view.setOnLongClickListener {
                viewModel.changeEnabledState(shopItem)
                true
            }
        }
    }
}