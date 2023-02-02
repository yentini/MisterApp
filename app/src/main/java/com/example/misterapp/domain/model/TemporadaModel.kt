package com.example.misterapp.domain.model

data class TemporadaModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val name: String
)
