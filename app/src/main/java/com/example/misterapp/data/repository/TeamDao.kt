package com.example.misterapp.data.repository

import androidx.room.*
import com.example.misterapp.core.Constants.Companion.TEAM_TABLE
import com.example.misterapp.core.Constants.Companion.TEMPORADAS_TABLE
import com.example.misterapp.data.TeamEntity
import com.example.misterapp.data.TemporadaEntity
import com.example.misterapp.data.TemporadaWithTeams
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM $TEAM_TABLE WHERE temporadaId = :temporadaId ORDER BY teamId ASC")
    fun getTeamsByTemporadaId(temporadaId: Int): Flow<List<TeamEntity>>

    @Query("SELECT * FROM $TEAM_TABLE ORDER BY teamId ASC")
    fun getAll(): Flow<List<TeamEntity>>

    @Query("SELECT * FROM $TEAM_TABLE WHERE teamId = :teamId")
    fun getTeam(teamId: Int): TeamEntity

    @Insert
    suspend fun addTeam(team: TeamEntity)

    @Update
    suspend fun updateTeam(team: TeamEntity)

    @Delete
    suspend fun deleteTeam(team: TeamEntity)

}