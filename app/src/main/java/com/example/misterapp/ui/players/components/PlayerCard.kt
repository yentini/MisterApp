package com.example.misterapp.ui.players.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.temporada.TemporadasViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerCard(
    playerModel: PlayerModel,
    navigateToPlayersScreen: (playerId: Int) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = 4.dp,
        onClick ={
            navigateToPlayersScreen(playerModel.playerId)
        }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                playerModel.name,
                Modifier.weight(1f))
            // Text(playerModel.email)
            // Text(playerModel.phone.toString())
            Text(
                text=playerModel.birthday.toString()
            )
        }
    }
}