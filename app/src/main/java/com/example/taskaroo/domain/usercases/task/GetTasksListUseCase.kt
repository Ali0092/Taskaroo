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
                if (it.isEmpty()){
                    emit(ViewState.Error(message = "No Data"))
                }else {
                    emit(ViewState.Success(it.map { it.toTask() }))
                }
            }
        } catch (e: Exception) {
            emit(ViewState.Error(message = e.message.toString()))
        }
    }

}