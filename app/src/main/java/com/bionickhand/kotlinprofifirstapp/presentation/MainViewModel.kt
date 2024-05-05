package com.bionickhand.kotlinprofifirstapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bionickhand.kotlinprofifirstapp.data.ShopListRepositoryImpl
import com.bionickhand.kotlinprofifirstapp.domain.DeleteShopItemUseCase
import com.bionickhand.kotlinprofifirstapp.domain.EditShopItemUseCase
import com.bionickhand.kotlinprofifirstapp.domain.GetShopItemListUseCase
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem

class MainViewModel: ViewModel() {
    private val shopListRepositoryImpl = ShopListRepositoryImpl

    private val getShopItemListUseCase = GetShopItemListUseCase(shopListRepositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepositoryImpl)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepositoryImpl)

    val shopItemList = getShopItemListUseCase.getShopItemList()

    fun changeEnabledState(shopItem: ShopItem){
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
}