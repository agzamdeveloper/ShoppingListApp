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
import androidx.recyclerview.widget.RecyclerView
import com.bionickhand.kotlinprofifirstapp.R
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopItemListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopItemList.observe(this){
            adapter.shopItemList = it
        }
    }

    private fun setupRecyclerView(){
        val rv = findViewById<RecyclerView>(R.id.recyclerViewShopItemList)
        adapter = ShopItemListAdapter()
        rv.adapter = adapter
        rv.recycledViewPool.setMaxRecycledViews(
            ShopItemListAdapter.VIEW_ENABLED,
            ShopItemListAdapter.MAX_POOL_SIZE
        )
        rv.recycledViewPool.setMaxRecycledViews(
            ShopItemListAdapter.VIEW_DISABLED,
            ShopItemListAdapter.MAX_POOL_SIZE
        )
    }

}