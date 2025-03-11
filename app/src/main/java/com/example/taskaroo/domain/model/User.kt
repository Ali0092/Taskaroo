package com.example.taskaroo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskaroo.data.model.UserDTO

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = "",
    var image: String = "",
    val preferences: List<String> = emptyList()
)

fun User.toUserDTO(): UserDTO {
    return UserDTO(
        id = id,
        name = name,
        image = image,
        preferences = preferences
    )
}