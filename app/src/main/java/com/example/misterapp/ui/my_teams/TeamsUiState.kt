package com.example.misterapp.ui.my_teams

import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel

sealed interface TeamsUiState{
    object Loading: TeamsUiState
    data class Error(val throwable: Throwable): TeamsUiState
    data class Success(val teams: List<TeamModel>): TeamsUiState
}