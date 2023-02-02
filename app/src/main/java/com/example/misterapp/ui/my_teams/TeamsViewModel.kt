package com.example.misterapp.ui.my_teams

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misterapp.core.Constants.Companion.NO_VALUE
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.domain.usecases.team.AddTeamUseCase
import com.example.misterapp.domain.usecases.team.GetTeamsUseCase
import com.example.misterapp.domain.usecases.temporada.*
import com.example.misterapp.ui.my_teams.TeamsUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val addTeamUseCase: AddTeamUseCase,
    private val getTemporadaUseCase: GetTemporadaUseCase
): ViewModel(){

    val uiState: StateFlow<TeamsUiState> = getTeamsUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TeamsUiState.Loading)

    var temporada by mutableStateOf(TemporadaModel(name = NO_VALUE))
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTeamCreated(
        temporadaId: Int,
        category: String,
        level: String,
        year: Int,
        club: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addTeamUseCase(
                TeamModel(
                    temporadaId = temporadaId,
                    category = category,
                    level = level,
                    year = year,
                    club = club
                )
            )
        }
    }

    fun getTemporada(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            temporada = getTemporadaUseCase(id)
        }
    }

    fun updateTemporadaName(name: String){
        temporada = temporada.copy(
            name = name
        )
    }
/*
    fun deleteTemporada(temporada: TemporadaModel){
        viewModelScope.launch(Dispatchers.IO) {
            deleteTemporadaUseCase(temporada)
        }
    }

    fun updateTemporadaName(name: String){
        temporada = temporada.copy(
            name = name
        )
    }

    fun updateTemporada(temporada: TemporadaModel){
        viewModelScope.launch(Dispatchers.IO) {
            updateTemporadaUseCase(temporada)
        }
    }

    fun getTeam(teamId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            temporada = getTemporadaUseCase(teamId)
        }
    }*/

}