package com.idcamp2020.made.core.di

import androidx.room.Room
import com.idcamp2020.made.core.BuildConfig
import com.idcamp2020.made.core.data.MovieRepository
import com.idcamp2020.made.core.data.source.local.LocalDataSource
import com.idcamp2020.made.core.data.source.local.room.FavoriteDatabase
import com.idcamp2020.made.core.data.source.remote.network.ApiService
import com.idcamp2020.made.core.data.source.remote.response.RemoteDataSource
import com.idcamp2020.made.core.domain.repository.IMovieRepository
import com.idcamp2020.made.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<FavoriteDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteDatabase::class.java,
            "movie_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
}
