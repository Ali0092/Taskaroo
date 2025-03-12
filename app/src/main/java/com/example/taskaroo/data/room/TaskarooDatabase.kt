package com.example.taskaroo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskaroo.data.model.TaskDTO
import com.example.taskaroo.data.model.UserDTO

@Database(entities = [TaskDTO::class, UserDTO::class], version = 1, exportSchema = false)
abstract class TaskarooDatabase : RoomDatabase() {

    abstract fun getTaaskDao(): TaskDao

    abstract fun getUserDao(): UserDao

}