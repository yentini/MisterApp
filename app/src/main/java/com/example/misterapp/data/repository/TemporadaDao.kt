package com.example.misterapp.data.repository

import androidx.room.*
import com.example.misterapp.core.Constants.Companion.TEMPORADAS_TABLE
import com.example.misterapp.data.TemporadaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TemporadaDao {

    @Query("SELECT * FROM $TEMPORADAS_TABLE ORDER BY id ASC")
    fun getTemporadas(): Flow<List<TemporadaEntity>>

    @Query("SELECT * FROM $TEMPORADAS_TABLE WHERE id = :id")
    fun getTemporada(id: Int): Flow<TemporadaEntity>

    @Query("SELECT count(*) FROM $TEMPORADAS_TABLE")
    fun getNumTemporadas(): Flow<Int>

    @Query("SELECT * FROM $TEMPORADAS_TABLE WHERE favorite = 1")
    fun getTemporada(): Flow<TemporadaEntity>

    @Insert
    suspend fun addTemporada(temporada: TemporadaEntity)

    @Update
    suspend fun updateTemporada(temporada: TemporadaEntity)

    @Update
    suspend fun updateTemporadas(temporada: List<TemporadaEntity>)

    @Delete
    suspend fun deleteTemporada(temporada: TemporadaEntity)

}