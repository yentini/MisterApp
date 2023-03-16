package com.example.misterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.misterapp.core.Constants
import java.time.LocalDate

@Entity(tableName = Constants.MATCHES_TABLE)
data class MatchEntity (
    @PrimaryKey
    val matchId: Int,
    val teamId: Int,
    val date: LocalDate,
    val opponent: String,
    val tournament: String,
    val local: Boolean
)