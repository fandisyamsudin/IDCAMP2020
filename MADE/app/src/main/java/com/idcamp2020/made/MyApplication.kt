package com.idcamp2020.made

import android.app.Application
import com.idcamp2020.made.core.di.databaseModule
import com.idcamp2020.made.core.di.networkModule
import com.idcamp2020.made.core.di.repositoryModule
import com.idcamp2020.made.di.useCaseModule
import com.idcamp2020.made.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}