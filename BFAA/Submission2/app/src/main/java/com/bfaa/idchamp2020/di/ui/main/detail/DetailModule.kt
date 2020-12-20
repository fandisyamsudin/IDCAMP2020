package com.bfaa.idchamp2020.di.ui.main.detail

import com.bfaa.idchamp2020.network.ApiHelper
import com.bfaa.idchamp2020.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DetailModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideProfileRepository(
        api: ApiHelper
    ) = UserRepository(api)
}