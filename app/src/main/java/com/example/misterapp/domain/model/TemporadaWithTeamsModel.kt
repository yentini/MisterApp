package com.example.misterapp.domain.model

import com.example.misterapp.data.TemporadaEntity

data class TemporadaWithTeamsModel (
    val temporada: TemporadaEntity,
    val teams: List<TeamModel>
)