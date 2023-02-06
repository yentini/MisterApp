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
import com.example.misterapp.domain.model.TeamModel

@Composable
fun TeamTopBar (
    team: TeamModel,
    navigateBack: () -> Unit
){
    TopAppBar (
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
    ) {
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