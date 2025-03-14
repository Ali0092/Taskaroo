package com.example.taskaroo.domain.usercases.user

import com.example.taskaroo.common.ViewState
import com.example.taskaroo.data.model.toUser
import com.example.taskaroo.domain.model.User
import com.example.taskaroo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserUserCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<ViewState<User>> = flow {
        try {
            emit(ViewState.Loading())
            userRepository.getUserData().collect { response ->
                val responseModel = if (response.isEmpty()) User() else response.first().toUser()
                emit(ViewState.Success(responseModel))
            }
        } catch (e: Exception) {
            emit(ViewState.Error(message = e.message.toString()))
        }
    }

}