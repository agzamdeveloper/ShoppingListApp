package com.bionickhand.kotlinprofifirstapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem
import com.bionickhand.kotlinprofifirstapp.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {
    private val shopItemListLD = MutableLiveData<List<ShopItem>>()
    private var shopItemList = sortedSetOf<ShopItem>(comparator = { o1, o2 -> o1.id.compareTo(o2.id) })
    private var installId = 0

    init {
        for (i in 0 until 100){
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = installId
            installId++
        }
        shopItemList.add(shopItem)
        updateShopItemListLD()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
        updateShopItemListLD()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        deleteShopItem(oldShopItem)
        addShopItem(shopItem)
    }

    override fun getShopItemList(): LiveData<List<ShopItem>> {
        return shopItemListLD
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopItemList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }

    private fun updateShopItemListLD(){
        shopItemListLD.value = shopItemList.toList()
    }
}