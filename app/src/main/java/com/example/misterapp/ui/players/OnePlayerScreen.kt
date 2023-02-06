package com.example.misterapp.ui.players

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
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.players.components.OnePlayerContent
import com.example.misterapp.ui.players.components.OnePlayerTopBar

@Composable
fun PlayerScreen(
    playersViewModel: PlayersViewModel = hiltViewModel(),
    playerId: Int,
    navigateBack: () -> Unit
){
    LaunchedEffect(Unit){
        playersViewModel.refresUiState(playerId)
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val isAddingPlayerEnable: Boolean by playersViewModel.isAddingPlayerEnable.observeAsState(initial = false)

    val uiOnePlayerState by produceState<PlayerUiState>(
        initialValue = PlayerUiState.Loading,
        key1 = lifecycle,
        key2 = playersViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            playersViewModel.uiOnePlayerState.collect{ value = it}
        }
    }

    when(uiOnePlayerState) {
        is PlayerUiState.Error -> {}
        PlayerUiState.Loading -> {
            ContentLoadingProgressBar()
        }
        is PlayerUiState.Success -> {
            Scaffold(
                topBar = { OnePlayerTopBar(
                                (uiOnePlayerState as PlayerUiState.Success).player,
                                navigateBack = navigateBack
                            )
                },
                content = { padding ->
                    OnePlayerContent(
                        (uiOnePlayerState as PlayerUiState.Success).player
                    )
                },
                floatingActionButton = {}/*{
                    AddPlayerFloatingActionButton(
                        playersViewModel
                    )
                }*/
            )
        }
    }
}