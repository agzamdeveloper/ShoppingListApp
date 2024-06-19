package com.bionickhand.kotlinprofifirstapp.di

import android.app.Application
import com.bionickhand.kotlinprofifirstapp.presentation.MainActivity
import com.bionickhand.kotlinprofifirstapp.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}