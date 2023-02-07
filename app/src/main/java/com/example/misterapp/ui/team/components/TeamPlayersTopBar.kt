package com.example.misterapp.ui.team.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.misterapp.core.Constants.Companion.TEAM_PLAYERS
import com.example.misterapp.domain.model.TeamModel

@Composable
fun TeamPlayersTopBar (
    navigateBack: () -> Unit
){
    TopAppBar (
        title = {
           Text(TEAM_PLAYERS)
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