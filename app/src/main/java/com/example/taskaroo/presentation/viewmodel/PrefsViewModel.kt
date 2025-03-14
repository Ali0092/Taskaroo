package com.example.taskaroo.presentation.viewmodel

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.domain.usercases.prefs.GetBooleanUseCase
import com.example.taskaroo.domain.usercases.prefs.SaveBooleanUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PrefsViewModel(
    val saveBooleanUseCase: SaveBooleanUseCase,
    val getBooleanUseCase: GetBooleanUseCase,
) : ViewModel() {

    private val _isOnBoardingDone = MutableStateFlow<Boolean?>(null)
    val isOnBoardingDone: StateFlow<Boolean?> = _isOnBoardingDone

    private val _isUserProfileCreated = MutableStateFlow<Boolean?>(null)
    val isUserProfileCreated: StateFlow<Boolean?> = _isUserProfileCreated

    init {
        viewModelScope.launch {
            getBooleanUseCase(DataStoreManager.ON_BOARDING_DONE_KEY).collect { data ->
                _isOnBoardingDone.value = data
            }
            getBooleanUseCase(DataStoreManager.USER_PROFILE_DONE_KEY).collect { data ->
                _isUserProfileCreated.value = data
            }
        }
    }

    fun saveBooleanPrefs(key: Preferences.Key<Boolean>, value: Boolean) {
        viewModelScope.launch {
            saveBooleanUseCase(key, value)
        }
    }

}