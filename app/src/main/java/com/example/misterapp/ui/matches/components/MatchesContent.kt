package com.example.misterapp.ui.matches.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.ui.matches.MatchesEvent
import com.example.misterapp.ui.matches.MatchesState
import com.example.misterapp.ui.matches.MatchesViewModel
import kotlinx.coroutines.launch

@Composable
fun MatchesContent(
    //navigateToPlayerScreen: (playerId: Int) -> Unit,
    matchesViewModel: MatchesViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    padding: PaddingValues,
    teamId: Int
) {
    val scope = rememberCoroutineScope()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val state by produceState<MatchesState>(
        initialValue = MatchesState(),
        key1 = lifecycle,
        key2 = matchesViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            matchesViewModel.uiState.collect{ value = it}
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding).padding(8.dp)
    ) {
        AnimatedVisibility(
            visible = state.isOrderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            MatchOrderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                matchOrder = state.matchOrder,
                onOrderChange = {
                    matchesViewModel.onEvent(MatchesEvent.Order(teamId, it))
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                state.matches,
                key = { it.matchId }
            ) { match ->
                MatchCard(
                    match,
                    //navigateToPlayerScreen,
                    onDeleteClick = {
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "¿Estás seguro?",
                                actionLabel = "Aceptar"
                            )
                            if(result == SnackbarResult.ActionPerformed) {
                                matchesViewModel.onEvent(MatchesEvent.DeleteMatch(match))
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

