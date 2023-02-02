package com.example.misterapp.ui.team

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.usecases.team.GetTeamUseCase
import com.example.misterapp.ui.players.PlayerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getTeamUseCase: GetTeamUseCase

): ViewModel() {

    private val _teamModel = MutableLiveData<TeamModel>()
    val teamModel : LiveData<TeamModel> = _teamModel

    fun refreshTeam(teamId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _teamModel.value = getTeamUseCase(teamId)
        }
    }

}