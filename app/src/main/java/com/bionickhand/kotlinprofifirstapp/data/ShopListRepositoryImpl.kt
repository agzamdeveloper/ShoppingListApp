package com.bionickhand.kotlinprofifirstapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bionickhand.kotlinprofifirstapp.domain.ShopItem
import com.bionickhand.kotlinprofifirstapp.domain.ShopListRepository

class ShopListRepositoryImpl(application: Application): ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopItemList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
        addSource(shopListDao.getItemList()){
            value = mapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun getShopItem(id: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(id)
        return mapper.mapDbModelToEntity(dbModel)
    }

}