package com.example.misterapp.domain.model

import java.time.LocalDate

data class MatchModel (
    val matchId: Int = System.currentTimeMillis().hashCode(),
    val teamId: Int,
    val date: LocalDate,
    val opponent: String,
    val tournament: String,
    val local: Boolean
)