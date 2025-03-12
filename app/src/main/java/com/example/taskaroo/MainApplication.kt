package com.example.taskaroo

import android.app.Application
import com.example.taskaroo.koin.appModule
import com.example.taskaroo.koin.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule, viewModelModules)
        }
    }

}
