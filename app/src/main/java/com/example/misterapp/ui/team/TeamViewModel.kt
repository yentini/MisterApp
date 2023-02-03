package com.example.misterapp.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.usecases.team.GetTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getTeamUseCase: GetTeamUseCase

): ViewModel() {

    var uiTeamState: StateFlow<TeamUiState> = getTeamUseCase(-1).map(TeamUiState::Success)
        .catch { TeamUiState.Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            TeamUiState.Loading
    )

    private val _teamModel = MutableLiveData<TeamModel>()
    val teamModel : LiveData<TeamModel> = _teamModel

    fun refreshTeam(teamId: Int){
        uiTeamState = getTeamUseCase(teamId).map(TeamUiState::Success)
            .catch { TeamUiState.Error(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                TeamUiState.Loading
            )
    }

}