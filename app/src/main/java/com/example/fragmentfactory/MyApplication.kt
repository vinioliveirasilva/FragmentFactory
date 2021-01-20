package com.example.fragmentfactory

import android.app.Application
import com.example.fragmentfactory.main.provider.SecurityStorage
import com.example.fragmentfactory.main.provider.SecurityStorageImpl
import com.example.fragmentfactory.network.AuthProvider
import com.example.fragmentfactory.network.AuthProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(module {
                single<AuthProvider> { AuthProviderImpl() }
                single<SecurityStorage> { SecurityStorageImpl() }
            })
        }
    }
}