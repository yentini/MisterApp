package com.example.misterapp.data.repository

import androidx.room.*
import com.example.misterapp.core.Constants.Companion.PLANTILLA
import com.example.misterapp.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamPlayersDao {

    @Query("SELECT * FROM $PLANTILLA WHERE teamId = :teamId AND playerId = :playerId")
    fun getPlayerTeam(teamId: Int, playerId: Int): Flow<TeamPlayersEntity>

    @Query("SELECT * FROM $PLANTILLA WHERE teamId = :teamId")
    fun getTeamPlayers(teamId: Int): Flow<List<TeamPlayersEntity>>

    @Insert
    suspend fun addPlayerToTeam(playerTeam: TeamPlayersEntity)

    @Update
    suspend fun updatePlayerTeam(playerTeam: TeamPlayersEntity)

    @Delete
    suspend fun deletePlayerTeam(playerTeam: TeamPlayersEntity)

}