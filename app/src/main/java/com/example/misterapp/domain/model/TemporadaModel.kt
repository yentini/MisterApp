package com.example.misterapp.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TemporadaModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val name: String,
    val favorite: Boolean
)