package com.example.taskaroo.data.repositoryImp

import com.example.taskaroo.data.model.UserDTO
import com.example.taskaroo.data.room.UserDao
import com.example.taskaroo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImp(val userDao: UserDao): UserRepository {

    override suspend fun createUser(userDTO: UserDTO) {
        userDao.createUser(userDTO)
    }

    override suspend fun updateUserData(userDTO: UserDTO) {
        userDao.updateUserData(userDTO)
    }

    override suspend fun getUserData(): Flow<List<UserDTO>> {
        return userDao.getUserData()
    }

}