package com.example.taskaroo.domain.usercases.task

import com.example.taskaroo.domain.model.Task
import com.example.taskaroo.domain.model.toTaskDTO
import com.example.taskaroo.domain.repository.TaskRepository

class UpdateTaskUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        taskRepository.updateTask(task.toTaskDTO())
    }

}