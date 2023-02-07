package com.example.misterapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.misterapp.core.Constants

@Entity(
    tableName = Constants.TEAM_PLAYERS_TABLE,
    primaryKeys = ["playerId","teamId"])
data class TeamPlayersEntity(
    val playerId: Int,
    val teamId: Int
)

data class  TeamPlayers(
    @Embedded val team: TeamEntity,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "playerId",
        associateBy = Junction(TeamPlayersEntity::class)
    )
    val teamPlayers: List<PlayerEntity>
)