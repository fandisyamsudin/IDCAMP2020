package com.bfaa.idchamp2020.di.app

import com.bfaa.idchamp2020.di.ui.main.MainActivityBuilderModule
import com.bfaa.idchamp2020.di.ui.main.MainModule
import com.bfaa.idchamp2020.di.ui.main.MainViewModelModule
import com.bfaa.idchamp2020.ui.main.MainActivity
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