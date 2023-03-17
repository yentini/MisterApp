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
    teamViewModel: TeamViewModel,
    teamId: Int,
    show: Boolean,
    onDismiss: () -> Unit,
    onTeamPlayersAdded: (Int, List<PlayerModel>) -> Unit,
    teamPlayers: List<PlayerModel>
) {

    val allPlayers by teamViewModel.allPlayers.collectAsState(emptyList())

    if (show && !allPlayers.isEmpty()) {
        Dialog(
            onDismissRequest = { onDismiss() }
        ) {
            Column() {
                LazyColumn() {
                    items(
                        allPlayers.filterNot { player ->  teamPlayers.map { teamPlayer -> teamPlayer.playerId }.contains(player.playerId)},
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
                            allPlayers.filter { it.isSelected.value }
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