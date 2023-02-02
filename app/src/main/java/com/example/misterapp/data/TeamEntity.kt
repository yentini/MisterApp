package com.example.misterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.misterapp.core.Constants

@Entity(tableName = Constants.TEAM_TABLE)
data class TeamEntity (
    @PrimaryKey
    val teamId: Int,
    val temporadaId: Int,
    var category: String,
    val level: String,
    val year: Int,
    val club: String
)