package com.bfaa.favoriteapp.ui.base


import com.bfaa.favoriteapp.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
        DaggerAppComponent.builder().application(this).build()
}