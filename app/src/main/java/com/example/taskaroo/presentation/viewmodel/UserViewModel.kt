package com.example.taskaroo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskaroo.common.ViewState
import com.example.taskaroo.domain.model.User
import com.example.taskaroo.domain.usercases.user.CreateUserUserCase
import com.example.taskaroo.domain.usercases.user.GetUserUserCase
import com.example.taskaroo.domain.usercases.user.UpdateUserUserCase
import com.example.taskaroo.presentation.viewstates.UserViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class UserViewModel(
    val createUserUserCase: CreateUserUserCase,
    val updateUserUserCase: UpdateUserUserCase,
    val getUserUserCase: GetUserUserCase): ViewModel() {

    private val _userData = MutableStateFlow(UserViewState())
    val userData: StateFlow<UserViewState> = _userData

    init {
        getUserData()
    }

    private fun getUserData() {
        if (_userData.value.user==null) {
            getUserUserCase().onEach { state->
                when(state){
                    is ViewState.Loading -> _userData.value = UserViewState(isLoading = true)
                    is ViewState.Success -> _userData.value = state.data?.let { UserViewState(user = it) }!!
                    is ViewState.Error -> _userData.value = UserViewState(error= state.message)
                }
            }.launchIn(viewModelScope)
        }
    }
    fun createUserData(user: User) {
        viewModelScope.launch {
            createUserUserCase(user)
        }
    }

    fun updateUserData(user: User) {
        viewModelScope.launch {
            updateUserUserCase(user)
        }
    }

}