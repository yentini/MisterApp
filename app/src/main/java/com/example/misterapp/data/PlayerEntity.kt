package com.example.misterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.misterapp.core.Constants
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = Constants.PLAYER_TABLE)
data class PlayerEntity(
    @PrimaryKey
    val playerId: Int,
    val name: String,
    val email: String,
    val phone: Int,
    val birthday: LocalDate
)

enum class PlayerEntityFields(val description: String){
    NAME("name"),
    BIRTHDAY("birthday")
}