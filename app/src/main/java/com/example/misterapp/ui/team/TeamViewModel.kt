package com.example.misterapp.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TeamPlayerModel
import com.example.misterapp.domain.usecases.player.GetAllPlayersUseCase
import com.example.misterapp.domain.usecases.team.GetTeamUseCase
import com.example.misterapp.domain.usecases.teamplayers.AddPlayersToTeamUseCase
import com.example.misterapp.domain.usecases.teamplayers.GetTeamPlayersUseCase
import com.example.misterapp.ui.players.PlayersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getTeamUseCase: GetTeamUseCase,
    private val getTeamPlayersUseCase: GetTeamPlayersUseCase,
    private val getAllPlayersUseCase: GetAllPlayersUseCase,
    private val addPlayersToTeamUseCase: AddPlayersToTeamUseCase

): ViewModel() {

    var uiTeamState: StateFlow<TeamUiState> = getTeamUseCase(-1).map(TeamUiState::Success)
        .catch { TeamUiState.Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            TeamUiState.Loading
        )

    var uiTeamPlayersState: StateFlow<PlayersUiState> = getTeamPlayersUseCase(-1).map(PlayersUiState::Success)
        .catch { PlayersUiState.Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PlayersUiState.Loading
        )

    val uiPlayersState: StateFlow<PlayersUiState> = getAllPlayersUseCase().map(PlayersUiState::Success)
        .catch { PlayersUiState.Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PlayersUiState.Loading
        )

    private val _teamModel = MutableLiveData<TeamModel>()
    val teamModel : LiveData<TeamModel> = _teamModel

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
       // _playerName.value = ""
       // _email.value = ""
       // _phone.value = ""
       // _birthday.value = ""
        _showDialog.value = true
    }

    fun refreshTeamPlayers(teamId: Int){
        uiTeamPlayersState  = getTeamPlayersUseCase(teamId).map(PlayersUiState::Success)
            .catch { PlayersUiState.Error(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                PlayersUiState.Loading
            )
    }

    fun refreshTeam(teamId: Int){
        uiTeamState = getTeamUseCase(teamId).map(TeamUiState::Success)
            .catch { TeamUiState.Error(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                TeamUiState.Loading
            )
    }

    fun onTeamPlayersAdded(
        teamId: Int,
        players: List<PlayerModel>
    ){
        var teamPlayerModel: TeamPlayerModel
        var teamPlayers: List<TeamPlayerModel> = players.map { TeamPlayerModel(it.playerId, teamId, it.number) }
        viewModelScope.launch(Dispatchers.IO) {
            addPlayersToTeamUseCase(teamPlayers)
        }
    }

}