package com.example.misterapp.ui.team.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.misterapp.R
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.ui.my_teams.TeamsViewModel
import com.example.misterapp.ui.my_teams.components.TeamCard
import com.example.misterapp.ui.players.components.PlayerCard

@Composable
fun TeamPlayersContent(
    teams: List<PlayerModel>
){

        LazyColumn() {
            items(
                teams,
                key = { it.playerId }
            ) {
                    player -> TeamPlayerCard(
                        player
                    )
            }
        }

}