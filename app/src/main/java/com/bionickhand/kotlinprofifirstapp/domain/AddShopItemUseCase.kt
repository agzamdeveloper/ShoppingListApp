package com.bionickhand.kotlinprofifirstapp.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}