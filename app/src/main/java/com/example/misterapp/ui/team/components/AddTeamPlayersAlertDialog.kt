package com.example.misterapp.ui.team.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.core.Constants.Companion.ADD
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.players.PlayersUiState
import com.example.misterapp.ui.team.TeamViewModel
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddTeamPlayersAlertDialog(
    teamViewModel: TeamViewModel = hiltViewModel(),
    teamId: Int,
    show: Boolean,
    onDismiss: () -> Unit,
    onTeamPlayersAdded: (Int, List<PlayerModel>) -> Unit,

    ) {

    if (show) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        val uiPlayersState by produceState<PlayersUiState>(
            initialValue = PlayersUiState.Loading,
            key1 = lifecycle,
            key2 = teamViewModel
        ) {
            lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                teamViewModel.uiPlayersState.collect { value = it }
            }
        }

        val uiTeamPlayersState by produceState<PlayersUiState>(
            initialValue = PlayersUiState.Loading,
            key1 = lifecycle,
            key2 = teamViewModel
        ) {
            lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                teamViewModel.uiTeamPlayersState.collect { value = it }
            }
        }

        when (uiPlayersState) {
            is PlayersUiState.Error -> {}
            PlayersUiState.Loading -> {
                ContentLoadingProgressBar()
            }
            is PlayersUiState.Success -> {
                Dialog(
                    onDismissRequest = { onDismiss() }
                ) {
                    Column() {
                        LazyColumn() {
                            items(
                                (uiPlayersState as PlayersUiState.Success).playerState.players.filter {
                                    !(uiTeamPlayersState as PlayersUiState.Success).playerState.players.map { it.playerId }.contains(it.playerId)
                                  }
                                ,
                                key = { it.playerId }
                            ) { player ->
                                TeamPlayerCardDialog(player)
                            }
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            onClick = {
                                onDismiss()
                                onTeamPlayersAdded(
                                    teamId,
                                    (uiPlayersState as PlayersUiState.Success).playerState.players.filter { it.isSelected.value }
                                )
                            }, modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth()
                            //enabled = isAddingPlayerEnable
                        ) {
                            Text(text = ADD)
                        }
                    }
                }
            }
        }
    }
}