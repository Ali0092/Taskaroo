package com.example.taskaroo.domain.usercases.prefs

import androidx.datastore.preferences.core.Preferences
import com.example.taskaroo.data.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow

class GetBooleanUseCase(
   private val dataStoreManager: DataStoreManager
) {

    operator fun invoke(key: Preferences.Key<Boolean>) : Flow<Boolean>{
        return dataStoreManager.getBooleanPrefs(key)
    }

}