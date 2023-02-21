package com.example.misterapp.ui.team.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.misterapp.core.ActionItem
import com.example.misterapp.core.Constants
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.ui.generic_components.OverflowMenuAction

@Composable
fun TeamTopBar (
    team: TeamModel,
    navigateBack: () -> Unit
){
    TopAppBar (
        title = {
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        team.club.uppercase().plus(" '").plus(team.level).uppercase().plus("'"),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(team.category.plus(" ".plus(team.year.toString())),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector =  Icons.Outlined.ArrowBack,
                    contentDescription = null
                )
            }
        },actions = {
            val (isExpanded, setExpanded) = remember { mutableStateOf(false) }
            OverflowMenuAction(
                isExpanded,
                setExpanded,
                listOf(
                    ActionItem(
                        name = Constants.EDIT,
                        action = {},
                        order = 1
                    )
                )
            )
        }
    )
}