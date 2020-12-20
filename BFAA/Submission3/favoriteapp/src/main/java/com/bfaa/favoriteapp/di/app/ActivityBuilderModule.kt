package com.bfaa.favoriteapp.di.app

import com.bfaa.favoriteapp.di.ui.main.MainActivityBuilderModule
import com.bfaa.favoriteapp.di.ui.main.MainModule
import com.bfaa.favoriteapp.di.ui.main.MainViewModelModule
import com.bfaa.favoriteapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityBuilderModule::class,
            MainViewModelModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity



}