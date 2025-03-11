package com.example.taskaroo.domain.usercases.task

import com.example.taskaroo.data.model.TaskDTO
import com.example.taskaroo.domain.model.Task
import com.example.taskaroo.domain.model.toTaskDTO
import com.example.taskaroo.domain.repository.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        taskRepository.deleteTask(task.toTaskDTO())
    }

}