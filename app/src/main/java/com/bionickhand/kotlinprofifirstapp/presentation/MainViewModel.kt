package com.bionickhand.kotlinprofifirstapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bionickhand.kotlinprofifirstapp.data.ShopListRepositoryImpl
import com.bionickhand.kotlinprofifirstapp.domain.DeleteShopItemUseCase
import com.bionickhand.kotlinprofifirstapp.domain.EditShopItemUseCase
import com.bionickhand.kotlinprofifirstapp.domain.GetShopItemListUseCase
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val shopListRepositoryImpl = ShopListRepositoryImpl(application)

    private val getShopItemListUseCase = GetShopItemListUseCase(shopListRepositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepositoryImpl)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepositoryImpl)

    val shopItemList = getShopItemListUseCase.getShopItemList()

    fun changeEnabledState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }
}