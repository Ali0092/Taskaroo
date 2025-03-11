package com.example.taskaroo.presentation.viewstates

import com.example.taskaroo.domain.model.User

data class UserViewState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)