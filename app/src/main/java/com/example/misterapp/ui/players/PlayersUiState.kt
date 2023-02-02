package com.example.misterapp.ui.players

import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel

sealed interface PlayersUiState{
    object Loading: PlayersUiState
    data class Error(val throwable: Throwable): PlayersUiState
    data class Success(val players: List<PlayerModel>): PlayersUiState
}