package com.example.misterapp.data

import androidx.room.Entity
import com.example.misterapp.core.Constants

@Entity(tableName = Constants.PLANTILLA, primaryKeys = ["playerId","teamId"])
data class TeamPlayersEntity(
    val playerId: Int,
    val teamId: Int,
    val number: Int
) {
}