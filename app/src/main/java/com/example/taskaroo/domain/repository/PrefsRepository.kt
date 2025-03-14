package com.example.taskaroo.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PrefsRepository {
    suspend fun saveBooleanPrefs(key: Preferences.Key<Boolean> , value: Boolean)
    fun getBooleanPrefs(key: Preferences.Key<Boolean>): Flow<Boolean?>
}