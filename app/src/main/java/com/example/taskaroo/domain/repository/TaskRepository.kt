package com.example.taskaroo.domain.repository

import com.example.taskaroo.data.model.TaskDTO
import com.example.taskaroo.data.model.UserDTO
import com.example.taskaroo.data.room.TaskDao

interface TaskRepository {

    suspend fun createTask(taskDTO: TaskDTO)

    suspend fun updateTask(taskDTO: TaskDTO)

    suspend fun deleteTask(taskDTO: TaskDTO)

    suspend fun getTasks(): List<TaskDTO>

}