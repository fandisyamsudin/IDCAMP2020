package com.bfaa.idchamp2020.di.ui.main.home

import com.bfaa.idchamp2020.network.ApiHelper
import com.bfaa.idchamp2020.repositories.HomeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object HomeModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideHomeRepository(
        api: ApiHelper
    ) = HomeRepository(api)
}