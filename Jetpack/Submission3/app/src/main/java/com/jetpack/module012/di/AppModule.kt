package com.jetpack.module012.di

import android.app.Application
import com.jetpack.module012.BuildConfig
import com.jetpack.module012.api.ApiInterface
import com.jetpack.module012.data.local.LocalDataSource
import com.jetpack.module012.data.remote.RemoteDataSource
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.Dao
import com.jetpack.module012.data.room.FavoriteDatabase
import com.jetpack.module012.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    companion object {
        @Singleton
        @Provides
        fun provideRemoteDataSource(apiInterface: ApiInterface): RemoteDataSource =
            RemoteDataSource(apiInterface)

        @Singleton
        @Provides
        fun provideCatalogRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): CatalogRepository = CatalogRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideFavoriteDatabase(application: Application): FavoriteDatabase =
            FavoriteDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideDao(favoriteDatabase: FavoriteDatabase): Dao =
            favoriteDatabase.dao()

        @Singleton
        @Provides
        fun provideLocalDataSource(dao: Dao): LocalDataSource =
            LocalDataSource(dao)

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: CatalogRepository): ViewModelFactory =
            ViewModelFactory(catalogRepository)

        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

        @Singleton
        @Provides
        fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder().apply {
                baseUrl(BuildConfig.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
            }.build()

        @Provides
        fun provideCatalogApi(retrofit: Retrofit): ApiInterface =
            retrofit.create(ApiInterface::class.java)


    }
}