package com.example.taskaroo.data.repositoryImp

import com.example.taskaroo.data.model.TaskDTO
import com.example.taskaroo.data.room.TaskDao
import com.example.taskaroo.domain.repository.TaskRepository

class TaskRepositoryImp(val taskDao: TaskDao) : TaskRepository {

    override suspend fun createTask(taskDTO: TaskDTO) {
        taskDao.createTask(taskDTO)
    }

    override suspend fun updateTask(taskDTO: TaskDTO) {
        taskDao.updateTask(taskDTO)
    }

    override suspend fun deleteTask(taskDTO: TaskDTO) {
        taskDao.deleteTask(taskDTO)
    }

    override suspend fun getTasks(): List<TaskDTO> {
        return taskDao.getTasks()
    }
}