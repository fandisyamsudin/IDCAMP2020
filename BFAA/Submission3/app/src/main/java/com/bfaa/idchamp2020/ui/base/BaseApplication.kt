package com.bfaa.idchamp2020.ui.base


import com.bfaa.idchamp2020.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
        DaggerAppComponent.builder().application(this).build()
}