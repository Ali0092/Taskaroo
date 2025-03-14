package com.example.taskaroo.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskaroo.data.model.UserDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(userDTO: UserDTO)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserData(userDTO: UserDTO)

    @Query("SELECT * FROM user")
    fun getUserData(): Flow<List<UserDTO>>

}