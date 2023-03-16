package com.example.misterapp.ui.matches

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.misterapp.core.Constants
import com.example.misterapp.core.Order
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.util.MatchOrder
import com.example.misterapp.ui.matches.components.AddMatchAlertDialog
import com.example.misterapp.ui.matches.components.AddMatchFloatingActionButton
import com.example.misterapp.ui.matches.components.MatchesContent
import com.example.misterapp.ui.matches.components.MatchesTopBar
import com.example.misterapp.ui.players.components.AddPlayerAlertDialog
import com.example.misterapp.ui.players.components.AddPlayerFloatingActionButton
import com.example.misterapp.ui.players.components.MyPlayersTopBar
import com.example.misterapp.ui.players.components.PlayersContent
import com.example.misterapp.ui.team.TeamUiState
import com.example.misterapp.ui.team.components.TeamTopBar
import com.example.misterapp.ui.temporada.components.BottomBar
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MatchesScreen(
    matchesViewModel: MatchesViewModel = hiltViewModel(),
    teamId: Int,
    navController: NavController,
    navigateBack: () -> Unit
){
    LaunchedEffect(Unit){
        matchesViewModel.onEvent(MatchesEvent.GetTeam(teamId))
        matchesViewModel.onEvent(MatchesEvent.Order(teamId, MatchOrder.Date(Order.ASC)))
    }

    val team by matchesViewModel.team.observeAsState(TeamModel())
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            MatchesTopBar(
                team=team,
                navigateBack = navigateBack
            )
         },
        content = { padding ->
            MatchesContent(
                //navigateToPlayerScreen = navigateToPlayerScreen,
                scaffoldState = scaffoldState,
                padding = padding,
                teamId = teamId
            )
            AddMatchAlertDialog(teamId=teamId)
        },
        floatingActionButton = {
            AddMatchFloatingActionButton()
        },
        bottomBar = { BottomBar(
            navController = navController
        ) },
        scaffoldState = scaffoldState
    )
}