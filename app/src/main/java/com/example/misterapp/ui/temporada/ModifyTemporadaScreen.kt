package com.example.misterapp.ui.temporada

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.temporada.components.ModifyTemporadaContent
import com.example.misterapp.ui.temporada.components.ModifyTemporadaTopBar

@Composable
fun ModifyTemporadaScreen(
    temporadasViewModel: TemporadasViewModel = hiltViewModel(),
    temporadaId: Int,
    navigateBack: () -> Unit
){
    LaunchedEffect(Unit){
        temporadasViewModel.refresUiOneTemporadaState(temporadaId)
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiOneTemporadaState by produceState<ModifyTemporadaUiState>(
        initialValue = ModifyTemporadaUiState.Loading,
        key1 = lifecycle,
        key2 = temporadasViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            temporadasViewModel.uiModifyTemporadaState.collect{ value = it}
        }
    }

    when(uiOneTemporadaState) {
        is ModifyTemporadaUiState.Error -> {}
        ModifyTemporadaUiState.Loading -> {
            ContentLoadingProgressBar()
        }
        is ModifyTemporadaUiState.Success -> {
            Scaffold(
                topBar = { ModifyTemporadaTopBar(
                    (uiOneTemporadaState as ModifyTemporadaUiState.Success).temporada,
                    navigateBack = navigateBack
                )},
                content = { padding ->
                    ModifyTemporadaContent(
                        (uiOneTemporadaState as ModifyTemporadaUiState.Success).temporada,
                        navigateBack = navigateBack
                    )
                },
                floatingActionButton = {}
            )
        }
    }
}