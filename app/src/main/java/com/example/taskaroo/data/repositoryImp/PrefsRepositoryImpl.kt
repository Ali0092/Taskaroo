package com.example.taskaroo.data.repositoryImp

import androidx.datastore.preferences.core.Preferences
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.domain.repository.PrefsRepository
import kotlinx.coroutines.flow.Flow

class PrefsRepositoryImpl(private val dataStoreManager: DataStoreManager) : PrefsRepository{
    override suspend fun saveBooleanPrefs(
        key: Preferences.Key<Boolean>, value: Boolean
    ) {
        dataStoreManager.saveBooleanPrefs(key, value)
    }

    override fun getBooleanPrefs(key: Preferences.Key<Boolean>): Flow<Boolean?> {
       return dataStoreManager.getBooleanPrefs(key)
    }

}