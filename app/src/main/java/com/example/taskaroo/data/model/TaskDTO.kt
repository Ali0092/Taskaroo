package com.example.taskaroo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskaroo.domain.model.Task

@Entity(tableName = "task")
data class TaskDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var title: String = "",
    var description: String = "",
    var dueDate: Long = 0L,
    var startDate: Long = 0L,
    var priority: String = "",
    var category: String = "",
)

fun TaskDTO.toTask(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate,
        startDate = startDate,
        priority = priority,
        category = category
    )
}