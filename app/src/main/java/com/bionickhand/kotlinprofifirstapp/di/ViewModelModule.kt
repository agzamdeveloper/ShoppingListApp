package com.bionickhand.kotlinprofifirstapp.di

import androidx.lifecycle.ViewModel
import com.bionickhand.kotlinprofifirstapp.presentation.MainViewModel
import com.bionickhand.kotlinprofifirstapp.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    @Binds
    fun bindShopItemViewModel(viewModel: ShopItemViewModel): ViewModel
}