package com.example.misterapp.ui.my_teams

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.my_teams.components.AddTeamAlertDialog
import com.example.misterapp.ui.my_teams.components.AddTeamFloatingActionButton
import com.example.misterapp.ui.my_teams.components.MyTeamsContent
import com.example.misterapp.ui.my_teams.components.MyTeamsTopBar
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun MyTeamsScreen(
    temporadaViewModel: TemporadasViewModel = hiltViewModel(),
    teamViewModel: TeamsViewModel = hiltViewModel(),
    temporadaId: Int,
    navigateToMyTeamScreen: (teamId: Int) -> Unit,
    navigateBack: () -> Unit
){
    LaunchedEffect(Unit){
        teamViewModel.getTemporada(temporadaId)
    }

    val showDialog: Boolean by teamViewModel.showDialog.observeAsState(false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<TeamsUiState>(
        initialValue = TeamsUiState.Loading,
        key1 = lifecycle,
        key2 = teamViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            teamViewModel.uiState.collect{ value = it}
        }
    }

    when(uiState){
        is TeamsUiState.Error -> {}
        TeamsUiState.Loading -> {
            ContentLoadingProgressBar()
        }
        is TeamsUiState.Success -> {
            Scaffold(
                topBar = {
                    MyTeamsTopBar(
                        temporada = teamViewModel.temporada,
                        navigateBack = navigateBack,
                        navigateTemporadas = navigateBack,
                        deleteTemporada = { temporadaViewModel.deleteTemporada(it) }
                    )
                },
                content = { padding ->
                    MyTeamsContent(
                        (uiState as TeamsUiState.Success).teams.filter {
                                team -> team.temporadaId == temporadaId
                       },
                        teamViewModel,
                        navigateToMyTeamScreen = navigateToMyTeamScreen
                    )
                    AddTeamAlertDialog(
                        showDialog,
                        onDismiss = { teamViewModel.onDialogClose() },
                        onTeamAdded = {
                                temporadaId: Int, category: String, level: String, year: Int, club: String ->
                                        teamViewModel.onTeamCreated(temporadaId, category, level, year, club)
                        },
                        temporadaId
                    )
                },
                floatingActionButton = {
                    AddTeamFloatingActionButton(
                        teamViewModel
                    )
                }
            )
        }
    }

}