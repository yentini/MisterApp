package com.example.misterapp.data

import androidx.room.Embedded
import androidx.room.Relation

data class TemporadaWithTeams (
    @Embedded val temporada: TemporadaEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "temporadaId"
    )
    val teams: List<TeamEntity>?
)