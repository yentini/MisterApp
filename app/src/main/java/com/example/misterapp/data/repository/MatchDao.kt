package com.example.misterapp.data.repository

import android.service.autofill.FieldClassification.Match
import androidx.room.*
import com.example.misterapp.core.Constants
import com.example.misterapp.core.Constants.Companion.MATCHES_TABLE
import com.example.misterapp.core.Constants.Companion.PLAYER_TABLE
import com.example.misterapp.core.Order
import com.example.misterapp.data.MatchEntity
import com.example.misterapp.data.PlayerEntity
import com.example.misterapp.data.TemporadaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {

    @Query("SELECT * FROM $MATCHES_TABLE WHERE matchId = :matchId")
    fun getMatch(matchId: Int): Flow<MatchEntity>

    @Query("SELECT * FROM $MATCHES_TABLE WHERE teamId = :teamId ORDER BY matchId ASC")
    fun getAll(teamId: Int): Flow<List<MatchEntity>>

    @Insert
    suspend fun addMatch(player: MatchEntity)

    @Update
    suspend fun updateMatch(player: MatchEntity)

    @Delete
    suspend fun deleteMatch(player: MatchEntity)

}