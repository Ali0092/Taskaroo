package com.example.taskaroo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskaroo.data.model.TaskDTO

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var title: String = "",
    var description: String = "",
    var dueDate: Long = 0L,
    var startDate: Long = 0L,
    var priority: String = "",
    var category: String = "",
)

fun Task.toTaskDTO(): TaskDTO {
    return TaskDTO(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate,
        startDate = startDate,
        priority = priority,
        category = category
    )
}