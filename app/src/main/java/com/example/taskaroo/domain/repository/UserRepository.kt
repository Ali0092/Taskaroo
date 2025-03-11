package com.example.taskaroo.domain.repository

import com.example.taskaroo.data.model.UserDTO
import com.example.taskaroo.data.room.TaskDao

interface UserRepository {

    suspend fun createUser(userDTO: UserDTO)

    suspend fun updateUserData(userDTO: UserDTO)

    suspend fun getUserData(): UserDTO

}