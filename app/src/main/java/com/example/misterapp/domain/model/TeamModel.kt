package com.example.misterapp.domain.model

data class TeamModel (
    val teamId: Int = System.currentTimeMillis().hashCode(),
    val temporadaId: Int,
    var category: String,
    val level: String,
    val year: Int,
    val club: String
)