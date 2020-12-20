package com.bfaa.idchamp2020.di.ui.main

import com.bfaa.idchamp2020.di.FavoriteScope
import com.bfaa.idchamp2020.di.HomeScope
import com.bfaa.idchamp2020.di.ProfileScope
import com.bfaa.idchamp2020.di.SettingScope
import com.bfaa.idchamp2020.di.ui.main.detail.DetailModule
import com.bfaa.idchamp2020.di.ui.main.favorite.FavoriteModule
import com.bfaa.idchamp2020.di.ui.main.home.HomeModule
import com.bfaa.idchamp2020.di.ui.main.setting.SettingModule
import com.bfaa.idchamp2020.ui.main.detail.DetailFragment
import com.bfaa.idchamp2020.ui.main.favorite.FavoriteFragment
import com.bfaa.idchamp2020.ui.main.follow.ProfileFollowFragment
import com.bfaa.idchamp2020.ui.main.home.HomeFragment
import com.bfaa.idchamp2020.ui.main.setting.SettingFragment
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

    @FavoriteScope
    @ContributesAndroidInjector(
        modules = [
            FavoriteModule::class
        ]
    )
    abstract fun contributeFavoriteFragment(): FavoriteFragment

    @SettingScope
    @ContributesAndroidInjector(
        modules = [
            SettingModule::class
        ]
    )
    abstract fun contributeSettingFragment(): SettingFragment
}