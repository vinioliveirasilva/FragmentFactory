package com.example.academia

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(module {
                single<WorkoutStorage> {
                    WorkoutStorageImpl()
                }
            })
        }
    }
}