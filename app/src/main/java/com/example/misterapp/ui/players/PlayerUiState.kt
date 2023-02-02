package com.example.misterapp.ui.players

import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel

sealed interface PlayerUiState{
    object Loading: PlayerUiState
    data class Error(val throwable: Throwable): PlayerUiState
    data class Success(val player: PlayerModel): PlayerUiState
}