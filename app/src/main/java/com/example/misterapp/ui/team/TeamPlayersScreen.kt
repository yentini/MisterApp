package com.example.misterapp.ui.team

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
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.my_teams.TeamsUiState
import com.example.misterapp.ui.players.PlayersUiState
import com.example.misterapp.ui.players.components.AddPlayerFloatingActionButton
import com.example.misterapp.ui.team.components.*
import java.time.LocalDate

@Composable
fun TeamPlayersScreen(
    teamViewModel: TeamViewModel = hiltViewModel(),
    teamId: Int,
    navigateBack: () -> Unit
){
    LaunchedEffect(Unit){
        teamViewModel.refreshTeamPlayers(teamId)
    }
    val showDialog: Boolean by teamViewModel.showDialog.observeAsState(false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiTeamPlayersState by produceState<PlayersUiState>(
        initialValue = PlayersUiState.Loading,
        key1 = lifecycle,
        key2 = teamViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            teamViewModel.uiTeamPlayersState.collect{ value = it}
        }
    }

    when(uiTeamPlayersState) {
        is PlayersUiState.Error -> {}
        PlayersUiState.Loading -> {
            ContentLoadingProgressBar()
        }
        is PlayersUiState.Success -> {
            Scaffold(
                topBar = {
                    TeamPlayersTopBar(
                        navigateBack = navigateBack
                    )
                },
                content = {
                    TeamPlayersContent(
                        (uiTeamPlayersState as PlayersUiState.Success).players
                    )
                    AddTeamPlayersAlertDialog(
                        onDismiss = { teamViewModel.onDialogClose() },
                        teamId = teamId,
                        onTeamPlayersAdded = {
                                teamId: Int, players: List<PlayerModel> ->
                                    teamViewModel.onTeamPlayersAdded(teamId, players)
                        },
                        show = showDialog
                    )
                },
                floatingActionButton = {
                    AddTeamPlayerFloatingActionButton()
                }
            )
        }
    }
}