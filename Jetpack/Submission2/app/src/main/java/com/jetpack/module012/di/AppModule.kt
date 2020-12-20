package com.jetpack.module012.di

import com.jetpack.module012.BuildConfig
import com.jetpack.module012.api.ApiInterface
import com.jetpack.module012.data.CatalogRepository
import com.jetpack.module012.data.RemoteDataSource
import com.jetpack.module012.utils.ViewModelFactory
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
                remoteDataSource: RemoteDataSource
        ): CatalogRepository = CatalogRepository(remoteDataSource)

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