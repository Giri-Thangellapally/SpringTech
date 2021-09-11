package com.jetpack.springtech

import android.app.Application
import com.jetpack.springtech.koin.contactsRepositoryModule
import com.jetpack.springtech.koin.databaseModule
import com.jetpack.springtech.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //start KOIN
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(databaseModule,viewModelModule,contactsRepositoryModule))
        }
    }
}