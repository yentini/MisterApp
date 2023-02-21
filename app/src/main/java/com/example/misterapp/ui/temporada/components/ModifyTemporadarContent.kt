package com.example.misterapp.ui.temporada.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.core.Constants
import com.example.misterapp.core.Constants.Companion.PLAYER_NAME
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.temporada.TemporadasEvent
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun ModifyTemporadaContent(
    temporada: TemporadaModel,
    temporadasViewModel: TemporadasViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val isAddingTemporadaEnable: Boolean by temporadasViewModel.isAddingTemporadaEnable.observeAsState(initial = false)
    val temporadaName by temporadasViewModel.temporadaName.observeAsState(Constants.NO_VALUE)

    LaunchedEffect(Unit){
        temporadasViewModel.onAddinValuesChanged(
            temporadaName = temporada.name,
        )
    }

    Column(Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = temporadaName,
            onValueChange = {
                temporadasViewModel.onAddinValuesChanged(temporadaName = it)
            },
            label = { Text(text = PLAYER_NAME) },
            placeholder = { Text(text = PLAYER_NAME) }
        )
        Spacer(modifier = Modifier.size(4.dp))
        Button(onClick = {
            temporadasViewModel.onEvent(
                TemporadasEvent.UpdateTemporada(temporada.copy(name = temporadaName))
            )
            navigateBack()
        }, modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
            enabled = isAddingTemporadaEnable
        ) {
            Text(text = Constants.UPDATE)
        }
    }
}

