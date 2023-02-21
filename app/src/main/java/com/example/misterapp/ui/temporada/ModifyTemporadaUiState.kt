package com.example.misterapp.ui.temporada

import com.example.misterapp.domain.model.TemporadaModel

sealed interface ModifyTemporadaUiState{
    object Loading: ModifyTemporadaUiState
    data class Error(val throwable: Throwable): ModifyTemporadaUiState
    data class Success(val temporada: TemporadaModel): ModifyTemporadaUiState
}