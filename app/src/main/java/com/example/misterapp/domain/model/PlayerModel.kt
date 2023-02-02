package com.example.misterapp.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class PlayerModel (
    val playerId: Int = System.currentTimeMillis().hashCode(),
    val name: String,
    val email: String,
    val phone: Int,
    val birthday: LocalDate
)