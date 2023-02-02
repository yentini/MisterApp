package com.example.misterapp.ui.temporada

import com.example.misterapp.domain.model.TemporadaModel

sealed interface TemporadasUiState{
    object Loading: TemporadasUiState
    data class Error(val throwable: Throwable): TemporadasUiState
    data class Success(val temporadas: List<TemporadaModel>): TemporadasUiState
}