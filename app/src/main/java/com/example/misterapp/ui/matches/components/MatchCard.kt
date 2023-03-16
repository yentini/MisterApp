package com.example.misterapp.ui.matches.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.misterapp.core.Local
import com.example.misterapp.domain.model.MatchModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MatchCard(
    matchModel: MatchModel,
    //navigateToPlayersScreen: (playerId: Int) -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = 4.dp,
        onClick = {
            //navigateToPlayersScreen(playerModel.playerId)
        }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                matchModel.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            )
            Text(
                matchModel.tournament
            )
            Text(
                Local.valueOf(matchModel.local.toString().uppercase()).description
            )
            Text(
                matchModel.opponent
            )
            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete match",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}