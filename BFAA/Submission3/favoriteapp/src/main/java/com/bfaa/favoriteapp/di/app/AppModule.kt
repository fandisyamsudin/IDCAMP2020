package com.bfaa.favoriteapp.di.app

import com.bfaa.favoriteapp.network.ApiHelper
import com.bfaa.favoriteapp.repositories.UserRepository
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


    @Singleton
    @JvmStatic
    @Provides
    fun provideUserRepository() = UserRepository()


}