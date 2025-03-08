package com.example.taskaroo.koin

import com.example.taskaroo.data.datastore.DataStoreManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { DataStoreManager(androidContext()) }
}