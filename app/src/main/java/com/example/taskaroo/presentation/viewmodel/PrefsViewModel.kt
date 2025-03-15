package com.example.taskaroo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PrefsViewModel() : ViewModel() {

    private val _isSplashLoading = MutableStateFlow(true)
    val isSplashLoading = _isSplashLoading.asStateFlow()

    fun setSplashLoadingStateFalse() {
        _isSplashLoading.value = false
    }

    fun setSplashLoadingStateTrue() {
        _isSplashLoading.value = true
    }

}