package com.jetpack.module012.di

import com.jetpack.module012.ui.detail.DetailActivity
import com.jetpack.module012.ui.favorite.FavoriteActivity
import com.jetpack.module012.ui.home.HomeActivity
import com.jetpack.module012.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteActivity(): FavoriteActivity
}