package com.example.taskaroo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskaroo.data.model.UserDTO

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    val preferences: String = ""
)

fun User.toUserDTO(): UserDTO {
    return UserDTO(
        id = id,
        firstName = firstName,
        lastName = lastName,
        preferences = preferences
    )
}