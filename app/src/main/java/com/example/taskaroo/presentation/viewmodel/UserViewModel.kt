package com.example.taskaroo.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskaroo.common.ViewState
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.domain.model.User
import com.example.taskaroo.domain.usercases.prefs.GetBooleanUseCase
import com.example.taskaroo.domain.usercases.prefs.SaveBooleanUseCase
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
    val getUserUserCase: GetUserUserCase
): ViewModel() {

    private val _userData = MutableStateFlow(UserViewState())
    val userData: StateFlow<UserViewState> = _userData

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    init {
        _user.value = User()
        getUserData()
    }


    fun setUser(user: User) {
        _user.value = user
    }

    private fun getUserData() {
        Log.d("getUserDataLogsd", "getUserData: vm called")
        if (_userData.value.user==null) {
            Log.d("getUserDataLogsd", "getUserData: vm called its null")
            viewModelScope.launch {
                getUserUserCase().collect { state->
                    Log.d("getUserDataLogsd", "getUserData: vm called its null issode ${state.data}, ${state.message}")
                    when(state){
                        is ViewState.Loading -> _userData.value = UserViewState(isLoading = true)
                        is ViewState.Success -> _userData.value = state.data?.let { UserViewState(user = it) }!!
                        is ViewState.Error -> _userData.value = UserViewState(error= state.message)
                    }
                }
            }
        }
    }
    fun createUserData() {
        _user.value.let { it->
            viewModelScope.launch {
                createUserUserCase(it)
            }
        }
    }

    fun updateUserData(user: User) {
        viewModelScope.launch {
            updateUserUserCase(user)
        }
    }

}