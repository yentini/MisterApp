package com.example.misterapp.ui.team

import com.example.misterapp.domain.model.TeamModel

sealed interface TeamUiState{
    object Loading: TeamUiState
    data class Error(val throwable: Throwable): TeamUiState
    data class Success(val team: TeamModel): TeamUiState
}