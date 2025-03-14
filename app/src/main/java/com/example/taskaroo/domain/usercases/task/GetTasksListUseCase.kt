package com.example.taskaroo.domain.usercases.task

import com.example.taskaroo.common.ViewState
import com.example.taskaroo.data.model.toTask
import com.example.taskaroo.domain.model.Task
import com.example.taskaroo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTasksListUseCase(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Flow<ViewState<List<Task>>> = flow {

        try {
            emit(ViewState.Loading())
            taskRepository.getTasks().collect { it->
                val responseModel =
                    if (it.isEmpty()) listOf<Task>() else it.map { it.toTask() }
                emit(ViewState.Success(responseModel))
            }
        } catch (e: Exception) {
            emit(ViewState.Error(message = e.message.toString()))
        }
    }

}