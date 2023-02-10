package com.example.misterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.misterapp.core.Constants.Companion.TEMPORADAS_TABLE

@Entity (tableName = TEMPORADAS_TABLE)
data class TemporadaEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val favorite: Boolean
)