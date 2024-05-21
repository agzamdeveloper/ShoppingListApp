package com.bionickhand.kotlinprofifirstapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bionickhand.kotlinprofifirstapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopItemListAdapter: ShopItemListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopItemList.observe(this) {
            shopItemListAdapter.submitList(it)
        }
        val buttonAdd = findViewById<FloatingActionButton>(R.id.buttonAddShopItem)
        buttonAdd.setOnClickListener {
            val intent = ShopItemActivity.newIntentAddShopItem(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val rv = findViewById<RecyclerView>(R.id.recyclerViewShopItemList)
        with(rv) {
            shopItemListAdapter = ShopItemListAdapter()
            adapter = shopItemListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopItemListAdapter.VIEW_ENABLED,
                ShopItemListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopItemListAdapter.VIEW_DISABLED,
                ShopItemListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rv)
    }

    private fun setupSwipeListener(rv: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val shopItem = shopItemListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(shopItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    private fun setupClickListener() {
        shopItemListAdapter.onShopItemClickListener = {
            val intent = ShopItemActivity.newIntentEditShopItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupLongClickListener() {
        shopItemListAdapter.onShopItemLongClickListener = { viewModel.changeEnabledState(it) }
    }

}