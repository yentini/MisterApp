package com.example.misterapp.ui.team.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.misterapp.core.ActionItem
import com.example.misterapp.core.Constants.Companion.DELETE
import com.example.misterapp.core.Constants.Companion.EDIT
import com.example.misterapp.core.Constants.Companion.PLAYERS_SCREEN
import com.example.misterapp.core.Constants.Companion.PLAYER_SCREEN
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.generic_components.OverflowMenuAction

@Composable
fun TeamTopBar (
    team: TeamModel,
    navigateBack: () -> Unit
){
    TopAppBar (
        title = {
            Row() {
                Text(team.category)
                Text(team.level)
                Text(team.year.toString())
                Text(team.club)
            }
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector =  Icons.Outlined.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}