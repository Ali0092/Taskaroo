package com.example.taskaroo.domain.usercases.user

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
            val responseModel = if (response!=UserDTO()) User() else response.toUser()

            emit(ViewState.Success(responseModel))
        } catch (e: Exception) {
            emit(ViewState.Error(message = e.message.toString()))
        }
    }

}