package com.bfaa.favoriteapp.di.ui.main

import com.bfaa.favoriteapp.di.HomeScope
import com.bfaa.favoriteapp.di.ProfileScope
import com.bfaa.favoriteapp.di.ui.main.detail.DetailModule
import com.bfaa.favoriteapp.di.ui.main.home.HomeModule
import com.bfaa.favoriteapp.ui.main.detail.DetailFragment
import com.bfaa.favoriteapp.ui.main.follow.ProfileFollowFragment
import com.bfaa.favoriteapp.ui.main.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(
        modules = [
            HomeModule::class
        ]
    )
    abstract fun contributeHomeFragment(): HomeFragment

    @ProfileScope
    @ContributesAndroidInjector(
        modules = [
            DetailModule::class
        ]
    )
    abstract fun contributeProfileFragment(): DetailFragment

    @ProfileScope
    @ContributesAndroidInjector(
        modules = [
            DetailModule::class
        ]
    )
    abstract fun contributeProfileFollowFragment(): ProfileFollowFragment

}