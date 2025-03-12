package com.example.taskaroo.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskaroo.data.model.TaskDTO
import com.example.taskaroo.data.model.UserDTO

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(taskDTO: TaskDTO)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(taskDTO: TaskDTO)

    @Delete
    suspend fun deleteTask(taskDTO: TaskDTO)

    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<TaskDTO>

}