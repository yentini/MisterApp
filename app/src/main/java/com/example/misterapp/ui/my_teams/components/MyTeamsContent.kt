package com.example.misterapp.ui.my_teams.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.my_teams.TeamsViewModel
import com.example.misterapp.ui.temporada.TemporadasViewModel
import com.example.misterapp.ui.temporada.components.TemporadaCard

@Composable
fun MyTeamsContent(
    teams: List<TeamModel>,
    teamsViewModel: TeamsViewModel,
    navigateToMyTeamScreen: (teamId: Int) -> Unit
){
    Column() {
        Text("Mis equipos",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn() {
            items(
                teams, key = { it.teamId }
            ) {
                team -> TeamCard(
                        team,
                        navigateToMyTeamScreen
                    )
            }
        }
    }

}