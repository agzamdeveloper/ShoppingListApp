package com.bionickhand.kotlinprofifirstapp.di

import android.app.Application
import com.bionickhand.kotlinprofifirstapp.data.AppDatabase
import com.bionickhand.kotlinprofifirstapp.data.ShopListDao
import com.bionickhand.kotlinprofifirstapp.data.ShopListRepositoryImpl
import com.bionickhand.kotlinprofifirstapp.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}