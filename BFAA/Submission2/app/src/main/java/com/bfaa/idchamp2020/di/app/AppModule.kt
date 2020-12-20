package com.bfaa.idchamp2020.di.app

import com.bfaa.idchamp2020.network.ApiHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Singleton
    @JvmStatic
    @Provides
    fun provideApiHelper(): ApiHelper =
        ApiHelper.create()
}