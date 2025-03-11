package com.example.taskaroo.presentation.viewstates

import com.example.taskaroo.domain.model.Task

data class TasksViewState(
    val isLoading: Boolean = false,
    val tasks: List<Task>? = null,
    val error: String? = null
)