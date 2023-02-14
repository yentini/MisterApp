package com.example.misterapp.ui.players

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
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
import kotlinx.coroutines.flow.collect
import java.time.LocalDate

@Composable
fun PlayersScreen(
    navigateToPlayerScreen: (playerId: Int) -> Unit,
    navigateBack: () -> Unit
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = { MyPlayersTopBar(
            navigateBack = navigateBack
        )},
        content = { padding ->
            PlayersContent(
                navigateToPlayerScreen = navigateToPlayerScreen
            )
            AddPlayerAlertDialog()
        },
        floatingActionButton = {
            AddPlayerFloatingActionButton()
        },
        scaffoldState = scaffoldState
    )
}