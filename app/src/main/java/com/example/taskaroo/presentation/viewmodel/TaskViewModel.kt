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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getTasksListUseCase: GetTasksListUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _tasks = MutableStateFlow(TasksViewState())
    val tasks: StateFlow<TasksViewState> = _tasks

    private val _taskToBeAdded = MutableStateFlow<Task>(Task())
    val taskToBeAdded: StateFlow<Task> = _taskToBeAdded

    private val _selectedTask = MutableStateFlow<Task>(Task())
    val selectedTask: StateFlow<Task> = _selectedTask

    init {
        getTaskLists()
        _tasks.value = TasksViewState()
        _taskToBeAdded.value = Task()
        _selectedTask.value = Task(priority = "Low", category = "Default", startDate = System.currentTimeMillis(), dueDate = System.currentTimeMillis())
    }

    fun setSelectedTask(task: Task) {
        _selectedTask.value = task
        _taskToBeAdded.value = task
    }

    private fun getTaskLists() {
        getTasksListUseCase.invoke().onEach { state ->
            when (state) {
                is ViewState.Loading -> _tasks.value = TasksViewState(isLoading = true)
                is ViewState.Success -> _tasks.value =
                    state.data?.let { TasksViewState(tasks = it) }!!

                else -> _tasks.value = TasksViewState(error = state.message)
            }
        }.launchIn(viewModelScope)
    }

    fun setTaskToBeAdded(task: Task) {
        _taskToBeAdded.value = task
    }

    fun createTask() {
        _taskToBeAdded.value.let { task ->
            viewModelScope.launch {
                createTaskUseCase(task)
            }
        }
    }

    fun updateTask() {
        _taskToBeAdded.value.let { task ->
            viewModelScope.launch {
                updateTaskUseCase(task)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

}