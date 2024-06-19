package com.bionickhand.kotlinprofifirstapp.domain

import android.app.Application
import com.bionickhand.kotlinprofifirstapp.di.DaggerApplicationComponent

class ShopItemApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}