package com.example.misterapp.data.repository

import androidx.room.*
import com.example.misterapp.core.Constants.Companion.PLAYER_TABLE
import com.example.misterapp.core.Constants.Companion.TEAM_TABLE
import com.example.misterapp.core.Constants.Companion.TEMPORADAS_TABLE
import com.example.misterapp.data.PlayerEntity
import com.example.misterapp.data.TeamEntity
import com.example.misterapp.data.TemporadaEntity
import com.example.misterapp.data.TemporadaWithTeams
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query("SELECT * FROM $PLAYER_TABLE WHERE playerId = :playerId")
    fun getPlayer(playerId: Int): Flow<PlayerEntity>

    @Query("SELECT * FROM $PLAYER_TABLE ORDER BY playerId ASC")
    fun getAll(): Flow<List<PlayerEntity>>

    @Insert
    suspend fun addPlayer(player: PlayerEntity)

    @Update
    suspend fun updatePlayer(player: PlayerEntity)

    @Delete
    suspend fun deletePlayer(player: PlayerEntity)

}