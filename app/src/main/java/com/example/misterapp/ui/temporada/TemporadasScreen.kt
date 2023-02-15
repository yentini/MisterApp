package com.example.misterapp.ui.temporada

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.misterapp.core.Constants.Companion.TEMPORADAS_SCREEN
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.temporada.components.AddTemporadaAlertDialog
import com.example.misterapp.ui.temporada.components.AddTemporadaFloatingActionButton
import com.example.misterapp.ui.temporada.components.BottomBar
import com.example.misterapp.ui.temporada.components.TemporadasContent

@Composable
fun TemporadasScreen(
    navController: NavController,
    temporadasViewModel: TemporadasViewModel = hiltViewModel(),
    navigateToMyTeamsScreen: (temporadaId: Int) -> Unit
){
    val showDialog: Boolean by temporadasViewModel.showDialog.observeAsState(false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<TemporadasUiState>(
        initialValue = TemporadasUiState.Loading,
        key1 = lifecycle,
        key2 = temporadasViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            temporadasViewModel.uiState.collect{ value = it}
        }
    }

    when(uiState){
        is TemporadasUiState.Error -> {}
        TemporadasUiState.Loading -> {
            ContentLoadingProgressBar()
        }
        is TemporadasUiState.Success -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(TEMPORADAS_SCREEN)
                    })
                },
                content ={ padding ->
                    TemporadasContent(
                        (uiState as TemporadasUiState.Success).temporadas.filter { it.favorite == true }.firstOrNull(),
                        (uiState as TemporadasUiState.Success).temporadas,
                        navigateToMyTeamsScreen = navigateToMyTeamsScreen
                    )
                    AddTemporadaAlertDialog(
                        show = showDialog,
                        onDismiss = { temporadasViewModel.onDialogClose() },
                        onTemporadaAdded = { temporadaName: String, favorite: Boolean ->
                                                temporadasViewModel.onTemporadaCreated(temporadaName, favorite)
                        }
                    )
                },
                floatingActionButton = {
                    AddTemporadaFloatingActionButton(
                        temporadasViewModel
                    )
                },
                bottomBar = { BottomBar(
                    navController = navController
                ) }
            )
        }
    }




}