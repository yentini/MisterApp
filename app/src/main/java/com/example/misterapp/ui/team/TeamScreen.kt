package com.example.misterapp.ui.team

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.ui.my_teams.components.AddTeamAlertDialog
import com.example.misterapp.ui.my_teams.components.AddTeamFloatingActionButton
import com.example.misterapp.ui.my_teams.components.MyTeamsContent
import com.example.misterapp.ui.my_teams.components.MyTeamsTopBar
import com.example.misterapp.ui.players.PlayerUiState
import com.example.misterapp.ui.players.PlayersViewModel
import com.example.misterapp.ui.players.components.OnePlayerContent
import com.example.misterapp.ui.players.components.OnePlayerTopBar
import com.example.misterapp.ui.team.components.TeamTopBar
import com.example.misterapp.ui.temporada.TemporadasUiState
import com.example.misterapp.ui.temporada.TemporadasViewModel
import com.example.misterapp.ui.temporada.components.AddTemporadaAlertDialog
import com.example.misterapp.ui.temporada.components.AddTemporadaFloatingActionButton

@Composable
fun TeamScreen(
    teamViewModel: TeamViewModel = hiltViewModel(),
    teamId: Int,
    navigateBack: () -> Unit
){
    LaunchedEffect(Unit){
        teamViewModel.refreshTeam(teamId)
    }

    Scaffold(
        topBar = { TeamTopBar(
            teamViewModel.teamModel.value!!,
            navigateBack = navigateBack
            )
        },
        content ={}/* { padding ->
            OnePlayerContent(
                (uiOnePlayerState as PlayerUiState.Success).player
            )
        }*/,
        floatingActionButton = {}
    )
}