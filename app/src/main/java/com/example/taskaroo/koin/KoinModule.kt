package com.example.taskaroo.koin

import androidx.room.Room
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.data.room.TaskarooDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {


    single {
        Room.databaseBuilder(androidContext(), TaskarooDatabase::class.java, "app_database")
    }

    single { get<TaskarooDatabase>().taskDao() }

    single { DataStoreManager(androidContext()) }
}