package com.example.taskaroo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskaroo.domain.model.User

@Entity(tableName = "user")
data class UserDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    val preferences: String = ""
)

fun UserDTO.toUser(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        preferences = preferences
    )
}