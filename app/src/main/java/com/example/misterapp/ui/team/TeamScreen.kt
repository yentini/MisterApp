package com.example.misterapp.ui.team

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.team.components.TeamContent
import com.example.misterapp.ui.team.components.TeamTopBar

@Composable
fun TeamScreen(
    teamViewModel: TeamViewModel = hiltViewModel(),
    teamId: Int,
    navigateBack: () -> Unit
){
    LaunchedEffect(Unit){
        teamViewModel.refreshTeam(teamId)
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiTeamState by produceState<TeamUiState>(
        initialValue = TeamUiState.Loading,
        key1 = lifecycle,
        key2 = teamViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            teamViewModel.uiTeamState.collect{ value = it}
        }
    }

    when(uiTeamState) {
        is TeamUiState.Error -> {}
        TeamUiState.Loading -> {
            ContentLoadingProgressBar()
        }
        is TeamUiState.Success -> {
            Scaffold(
                topBar = {
                    TeamTopBar(
                        (uiTeamState as TeamUiState.Success).team,
                        navigateBack = navigateBack
                    )
                },
                content = {
                    TeamContent(
                        (uiTeamState as TeamUiState.Success).team
                    )
                      }/* { padding ->
                    OnePlayerContent(
                        (uiOnePlayerState as PlayerUiState.Success).player
                    )
                }*/,
                floatingActionButton = {}
            )
        }
    }
}