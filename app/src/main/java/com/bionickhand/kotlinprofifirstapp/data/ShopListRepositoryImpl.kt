package com.bionickhand.kotlinprofifirstapp.data

import com.bionickhand.kotlinprofifirstapp.domain.ShopItem
import com.bionickhand.kotlinprofifirstapp.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
    private var shopItemList = mutableListOf<ShopItem>()
    private var installId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = installId
            installId++
        }
        shopItemList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        deleteShopItem(oldShopItem)
        addShopItem(shopItem)
    }

    override fun getShopItemList(): List<ShopItem> {
        return shopItemList
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopItemList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }
}