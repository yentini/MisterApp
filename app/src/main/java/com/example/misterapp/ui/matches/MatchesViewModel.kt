package com.example.misterapp.ui.matches

import android.service.autofill.FieldClassification.Match
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misterapp.core.Constants
import com.example.misterapp.core.Order
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.domain.usecases.match.AddMatchUseCase
import com.example.misterapp.domain.usecases.match.DeleteMatchUseCase
import com.example.misterapp.domain.usecases.match.GetMatchesFilterUseCase
import com.example.misterapp.domain.usecases.player.*
import com.example.misterapp.domain.usecases.team.GetTeamUseCase
import com.example.misterapp.domain.util.MatchOrder
import com.example.misterapp.domain.util.PlayerOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel  @Inject constructor(
    private val getMatchesFilterUseCase: GetMatchesFilterUseCase,
    private val addMatchUseCase: AddMatchUseCase,
    private val getTeamUseCase: GetTeamUseCase,
    private val deleteMatchUseCase: DeleteMatchUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<MatchesState> = MutableStateFlow(MatchesState())
    val uiState: StateFlow<MatchesState> = _uiState

    private val _team: MutableLiveData<TeamModel> = MutableLiveData(TeamModel())
    val team: LiveData<TeamModel> = _team

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _opponent = MutableLiveData<String>()
    val opponent: LiveData<String> = _opponent

    private val _tournament = MutableLiveData<String>()
    val tournament: LiveData<String> = _tournament

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _local = MutableLiveData<Boolean>()
    val local: LiveData<Boolean> = _local

    private val _isAddingMatchEnable = MutableLiveData<Boolean>()
    val isAddingMatchEnable: LiveData<Boolean> = _isAddingMatchEnable

    fun onDialogClose() {
        _showDialog.value = false
    }

    private fun getTeam(teamId: Int) {
        viewModelScope.launch {
            getTeamUseCase(teamId)
                .collect { team -> _team.value = team }
        }
    }

    fun onShowDialogClick() {
        _opponent.value = ""
        _tournament.value = ""
        _date.value = ""
        _local.value = true
        _showDialog.value = true
    }

    fun onAddinValuesChanged(date: String, opponent: String, tournament: String, local: Boolean){
        _date.value = date
        _opponent.value = opponent
        _tournament.value = tournament
        _local.value = local
        _isAddingMatchEnable.value = enableAddingMatch(opponent, tournament)
    }

    private fun enableAddingMatch(opponent: String, tournament: String) =
                !opponent.isEmpty() &&
                !tournament.isEmpty()

    fun onEvent(event: MatchesEvent) {
        when (event) {
            is MatchesEvent.GetTeam -> {
                getTeam(event.teamId)
            }
            is MatchesEvent.Order -> {
                if (_uiState.value.matchOrder::class == event.matchOrder::class &&
                    _uiState.value.matchOrder.orderType == event.matchOrder.orderType
                ) {
                    return
                }
                getMatches(event.teamId, event.matchOrder)
            }
            is MatchesEvent.AddMatch -> {
                viewModelScope.launch {
                    addMatchUseCase(event.match)
                }
            }
            is MatchesEvent.DeleteMatch ->{
                viewModelScope.launch {
                    deleteMatchUseCase(event.match)
                }
            }
            is MatchesEvent.ToggleOrderSection -> {
                _uiState.value = _uiState.value.copy(
                    isOrderSectionVisible = !_uiState.value.isOrderSectionVisible
                )
            }/*
            is PlayersEvent.DeletePlayer -> {
                viewModelScope.launch {
                    deletePlayerUseCase(event.player)
                }
            }
            is PlayersEvent.UpdatePlayer -> {
                viewModelScope.launch {
                    updatePlayerUseCase(event.player)
                }
            }*/
        }
    }

    private fun getMatches(teamId: Int, matchOrder: MatchOrder) {
        viewModelScope.launch {
            getMatchesFilterUseCase(teamId, matchOrder)
                .collect { matches ->
                    _uiState.value = _uiState.value.copy(
                        matches = matches,
                        matchOrder = matchOrder
                    )
                }
        }
    }
}