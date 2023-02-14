package com.example.misterapp.ui.players.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.core.ActionItem
import com.example.misterapp.core.Constants.Companion.DELETE
import com.example.misterapp.core.Constants.Companion.EDIT
import com.example.misterapp.core.Constants.Companion.PLAYERS_SCREEN
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.generic_components.OverflowMenuAction
import com.example.misterapp.ui.players.PlayersEvent
import com.example.misterapp.ui.players.PlayersViewModel

@Composable
fun MyPlayersTopBar (
    playersViewModel: PlayersViewModel = hiltViewModel(),
    navigateBack: () -> Unit
){
    TopAppBar (
        title = {
            Text(PLAYERS_SCREEN)
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector =  Icons.Outlined.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    playersViewModel.onEvent(PlayersEvent.ToggleOrderSection)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Sort,
                    contentDescription = "Sort"
                )
            }
        }
    )
}