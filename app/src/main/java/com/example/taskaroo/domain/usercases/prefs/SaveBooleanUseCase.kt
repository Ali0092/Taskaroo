package com.example.taskaroo.domain.usercases.prefs

import androidx.datastore.preferences.core.Preferences
import com.example.taskaroo.data.datastore.DataStoreManager

class SaveBooleanUseCase(
   private val dataStoreManager: DataStoreManager
) {

    suspend operator fun invoke(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStoreManager.saveBooleanPrefs(key, value)
    }

}