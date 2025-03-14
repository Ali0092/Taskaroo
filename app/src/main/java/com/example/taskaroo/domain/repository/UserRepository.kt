package com.example.taskaroo.domain.repository

import com.example.taskaroo.data.model.UserDTO
import com.example.taskaroo.data.room.TaskDao
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun createUser(userDTO: UserDTO)

    suspend fun updateUserData(userDTO: UserDTO)

    suspend fun getUserData(): Flow<List<UserDTO>>

}