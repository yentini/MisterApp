package com.example.misterapp.ui.players.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.ui.players.PlayersViewModel

@Composable
fun PlayersContent(
    players: List<PlayerModel>,
    navigateToPlayerScreen: (playerId: Int) -> Unit,
    playersViewModel: PlayersViewModel= hiltViewModel()
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(0.dp)) {
        items(
            players.sortedWith(PlayerModel.NameComparator(playersViewModel.nameOrderAsc.value!!)), key = { it.playerId }
        ) {
            player -> PlayerCard(
                        player,
                        navigateToPlayerScreen
                    )
        }
        item{
            Button(onClick = {
                playersViewModel.toggleNameOrder()
            }) {
                Text(text = "Ordena por nombre")
            }
        }
    }
}

