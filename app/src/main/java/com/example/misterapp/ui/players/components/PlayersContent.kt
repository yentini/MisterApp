package com.example.misterapp.ui.players.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.core.Order
import com.example.misterapp.ui.players.PlayersEvent
import com.example.misterapp.ui.players.PlayersState
import com.example.misterapp.ui.players.PlayersViewModel
import androidx.compose.material.icons.filled.Sort

@Composable
fun PlayersContent(
    navigateToPlayerScreen: (playerId: Int) -> Unit,
    playersViewModel: PlayersViewModel= hiltViewModel()
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val state by produceState<PlayersState>(
        initialValue = PlayersState(),
        key1 = lifecycle,
        key2 = playersViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            playersViewModel.uiState.collect{ value = it}
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        AnimatedVisibility(
            visible = state.isOrderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            OrderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                playerOrder = state.playerOrder,
                onOrderChange = {
                    playersViewModel.onEvent(PlayersEvent.Order(it))
                }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(0.dp)) {
            items(
                state.players,
                key = { it.playerId }
            ) { player ->
                PlayerCard(
                    player,
                    navigateToPlayerScreen
                )
            }
        }
    }
}

