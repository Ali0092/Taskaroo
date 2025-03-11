package com.example.taskaroo.domain.usercases.user

import com.example.taskaroo.domain.model.User
import com.example.taskaroo.domain.model.toUserDTO
import com.example.taskaroo.domain.repository.UserRepository

class CreateUserUserCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.createUser(user.toUserDTO())
    }

}