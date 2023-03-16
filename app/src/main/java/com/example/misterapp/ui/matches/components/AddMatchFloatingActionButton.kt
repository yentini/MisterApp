package com.example.misterapp.ui.matches.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.core.Constants.Companion.ADD_MATCH
import com.example.misterapp.core.Constants.Companion.ADD_PLAYER
import com.example.misterapp.ui.matches.MatchesViewModel
import com.example.misterapp.ui.players.PlayersViewModel
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun AddMatchFloatingActionButton(
    matchesViewModel: MatchesViewModel = hiltViewModel()
) {
    FloatingActionButton(
        onClick = { matchesViewModel.onShowDialogClick() },
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = ADD_MATCH
        )
    }
}