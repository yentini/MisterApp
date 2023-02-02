package com.example.misterapp.ui.players.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.players.PlayersViewModel
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun PlayersContent(
    players: List<PlayerModel>,
    navigateToPlayerScreen: (playerId: Int) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(0.dp)) {
        items(
            players, key = { it.playerId }
        ) {
            player -> PlayerCard(
                        player,
                        navigateToPlayerScreen
                    )
        }
    }
}

