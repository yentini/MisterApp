package com.example.misterapp.ui.my_teams

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.example.misterapp.core.Constants
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.my_teams.components.AddTeamAlertDialog
import com.example.misterapp.ui.my_teams.components.AddTeamFloatingActionButton
import com.example.misterapp.ui.my_teams.components.MyTeamsContent
import com.example.misterapp.ui.my_teams.components.MyTeamsTopBar
import com.example.misterapp.ui.temporada.TemporadasViewModel
import com.example.misterapp.ui.temporada.components.BottomBar

@Composable
fun MyTeamsScreen(
    navController: NavController,
    temporadaViewModel: TemporadasViewModel = hiltViewModel(),
    teamViewModel: TeamsViewModel = hiltViewModel(),
    temporadaId: Int,
    navigateToMyTeamScreen: (teamId: Int) -> Unit,
    navigateBack: () -> Unit,
    navigateModifyTemporada: ()-> Unit
){

    LaunchedEffect(Unit){
        teamViewModel.getTemporada(temporadaId)
    }

    val temporada by teamViewModel.temporada.observeAsState(TemporadaModel(name = Constants.NO_VALUE, favorite = false))


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
            val scaffoldState = rememberScaffoldState()
            Scaffold(
                topBar = {
                    MyTeamsTopBar(
                        temporada = temporada,
                        navigateBack = navigateBack,
                        navigateTemporadas = navigateBack,
                        deleteTemporada = { temporadaViewModel.deleteTemporada(it) },
                        navigateModifyTemporada = navigateModifyTemporada
                    )
                },
                content = { padding ->
                    MyTeamsContent(
                        (uiState as TeamsUiState.Success).teams.filter {
                                team -> team.temporadaId == temporadaId
                       },
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
                },
                bottomBar = { BottomBar(
                    navController = navController
                ) },
                scaffoldState = scaffoldState
            )
        }
    }

}