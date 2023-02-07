package com.example.misterapp.data.repository

import androidx.room.*
import com.example.misterapp.core.Constants.Companion.TEAM_PLAYERS_TABLE
import com.example.misterapp.core.Constants.Companion.TEAM_TABLE
import com.example.misterapp.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamPlayersDao {

    @Query("SELECT * FROM $TEAM_PLAYERS_TABLE WHERE teamId = :teamId AND playerId = :playerId")
    fun getPlayerTeam(teamId: Int, playerId: Int): Flow<TeamPlayersEntity>

    @Transaction
    @Query("SELECT * FROM $TEAM_TABLE WHERE teamId = :teamId")
    fun getTeamPlayers(teamId: Int): Flow<TeamPlayers>

    @Insert
    suspend fun addPlayersToTeam(playerTeam: List<TeamPlayersEntity>)

    @Update
    suspend fun updatePlayerTeam(playerTeam: TeamPlayersEntity)

    @Delete
    suspend fun deletePlayerTeam(playerTeam: TeamPlayersEntity)

}