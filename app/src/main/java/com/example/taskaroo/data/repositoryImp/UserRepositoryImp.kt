package com.example.taskaroo.data.repositoryImp

import com.example.taskaroo.data.model.UserDTO
import com.example.taskaroo.data.room.TaskDao
import com.example.taskaroo.domain.repository.UserRepository

class UserRepositoryImp(val taskDao: TaskDao): UserRepository {

    override suspend fun createUser(userDTO: UserDTO) {
        taskDao.createUser(userDTO)
    }

    override suspend fun updateUserData(userDTO: UserDTO) {
        taskDao.updateUserData(userDTO)
    }

    override suspend fun getUserData(): UserDTO {
        return taskDao.getUserData()
    }

}