package com.example.taskaroo.domain.usercases.user

import android.util.Log
import com.example.taskaroo.common.ViewState
import com.example.taskaroo.data.model.UserDTO
import com.example.taskaroo.data.model.toUser
import com.example.taskaroo.domain.model.User
import com.example.taskaroo.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class GetUserUserCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<ViewState<User>> = flow {
        try {
            emit(ViewState.Loading())
            val response = userRepository.getUserData()
            Log.d("getUserDataLogsd", "invoke: ${response}")
            val responseModel = if (response.isEmpty()) User() else response.first().toUser()

            emit(ViewState.Success(responseModel))
        } catch (e: Exception) {
            Log.d("getUserDataLogsd", "invoke: ${e.message.toString()}")

            emit(ViewState.Error(message = e.message.toString()))
        }
    }

}