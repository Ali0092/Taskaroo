package com.example.taskaroo.domain.usercases.user

import com.example.taskaroo.domain.model.User
import com.example.taskaroo.domain.model.toUserDTO
import com.example.taskaroo.domain.repository.UserRepository

class UpdateUserUserCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.updateUserData(user.toUserDTO())
    }

}