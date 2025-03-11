package com.example.taskaroo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskaroo.domain.model.User

@Entity(tableName = "user")
data class UserDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = "",
    var image: String = "",
    val preferences: List<String> = emptyList()
)

fun UserDTO.toUser(): User {
    return User(
        id = id,
        name = name,
        image = image,
        preferences = preferences
    )
}