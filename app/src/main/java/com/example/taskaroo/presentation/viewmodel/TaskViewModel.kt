package com.example.taskaroo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskaroo.common.ViewState
import com.example.taskaroo.domain.model.Task
import com.example.taskaroo.domain.usercases.task.CreateTaskUseCase
import com.example.taskaroo.domain.usercases.task.DeleteTaskUseCase
import com.example.taskaroo.domain.usercases.task.GetTasksListUseCase
import com.example.taskaroo.domain.usercases.task.UpdateTaskUseCase
import com.example.taskaroo.presentation.viewstates.TasksViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getTasksListUseCase: GetTasksListUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
): ViewModel() {

    private val _tasks = MutableStateFlow(TasksViewState())
    val task: StateFlow<TasksViewState> = _tasks

    init {
        getTaskLists()
    }

    private fun getTaskLists() {
        if (_tasks.value.tasks == null) {
            getTasksListUseCase.invoke().onEach { state ->
                when(state){
                    is ViewState.Loading -> _tasks.value = TasksViewState(isLoading = true)
                    is ViewState.Success -> _tasks.value = state.data?.let { TasksViewState(tasks = it) }!!
                    else -> _tasks.value = TasksViewState(error = state.message)
                }
            }
        }
    }

    fun createTask(task: Task) {
        viewModelScope.launch {
            createTaskUseCase(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

}