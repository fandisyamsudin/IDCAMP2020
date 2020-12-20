package com.bfaa.idchamp2020.di.ui.main.favorite

import com.bfaa.idchamp2020.repositories.FavoriteRepository
import com.bfaa.idchamp2020.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FavoriteModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideProfileRepository(
        userRepository: UserRepository
    ) = FavoriteRepository(userRepository)
}