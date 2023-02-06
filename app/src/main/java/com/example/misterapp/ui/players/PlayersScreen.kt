package com.example.misterapp.ui.players

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.players.components.AddPlayerAlertDialog
import com.example.misterapp.ui.players.components.AddPlayerFloatingActionButton
import com.example.misterapp.ui.players.components.MyPlayersTopBar
import com.example.misterapp.ui.players.components.PlayersContent
import java.time.LocalDate

@Composable
fun PlayersScreen(
    playersViewModel: PlayersViewModel = hiltViewModel(),
    navigateToPlayerScreen: (playerId: Int) -> Unit,
    navigateBack: () -> Unit
){
    val showDialog: Boolean by playersViewModel.showDialog.observeAsState(false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val isAddingPlayerEnable: Boolean by playersViewModel.isAddingPlayerEnable.observeAsState(initial = false)

    val uiState by produceState<PlayersUiState>(
        initialValue = PlayersUiState.Loading,
        key1 = lifecycle,
        key2 = playersViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            playersViewModel.uiState.collect{ value = it}
        }
    }

    when(uiState) {
        is PlayersUiState.Error -> {}
        PlayersUiState.Loading -> {
            ContentLoadingProgressBar()
        }
        is PlayersUiState.Success -> {
            Scaffold(
                topBar = { MyPlayersTopBar(
                    navigateBack = navigateBack
                )},
                content = { padding ->
                    PlayersContent(
                        (uiState as PlayersUiState.Success).players,
                        navigateToPlayerScreen = navigateToPlayerScreen
                    )
                    AddPlayerAlertDialog(
                        showDialog,
                        onDismiss = { playersViewModel.onDialogClose() },
                        onPlayerAdded = {name: String, email: String, phone: Int, birthday: LocalDate ->
                                        playersViewModel.onPlayerAdded(name, email, phone, birthday)}
                    )
                },
                floatingActionButton = {
                    AddPlayerFloatingActionButton(
                        playersViewModel
                    )
                }
            )
        }
    }
}