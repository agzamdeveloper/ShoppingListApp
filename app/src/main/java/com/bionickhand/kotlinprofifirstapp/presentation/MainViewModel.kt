package com.bionickhand.kotlinprofifirstapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bionickhand.kotlinprofifirstapp.domain.DeleteShopItemUseCase
import com.bionickhand.kotlinprofifirstapp.domain.EditShopItemUseCase
import com.bionickhand.kotlinprofifirstapp.domain.GetShopItemListUseCase
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor (
    private val getShopItemListUseCase : GetShopItemListUseCase,
    private val editShopItemUseCase : EditShopItemUseCase,
    private val deleteShopItemUseCase : DeleteShopItemUseCase
) : ViewModel() {

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