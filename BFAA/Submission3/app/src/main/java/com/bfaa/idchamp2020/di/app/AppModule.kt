package com.bfaa.idchamp2020.di.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.bfaa.idchamp2020.database.AppDatabase
import com.bfaa.idchamp2020.network.ApiHelper
import com.bfaa.idchamp2020.repositories.UserRepository
import com.bfaa.idchamp2020.ui.main.setting.SettingFragment.Companion.SHARE_PREFERENCE_NAME
import com.bfaa.idchamp2020.util.DATABASE_NAME
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
    fun provideAppDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        DATABASE_NAME
    ).allowMainThreadQueries().build()

    @Singleton
    @JvmStatic
    @Provides
    fun provideUserRepository() = UserRepository()


    @Singleton
    @JvmStatic
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE)
}