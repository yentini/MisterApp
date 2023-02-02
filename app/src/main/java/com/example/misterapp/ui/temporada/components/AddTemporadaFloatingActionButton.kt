package com.example.misterapp.ui.temporada.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.misterapp.core.Constants.Companion.ADD_TEMPORADA
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun AddTemporadaFloatingActionButton(
    temporadasViewModel: TemporadasViewModel
) {
    FloatingActionButton(
        onClick = { temporadasViewModel.onShowDialogClick() },
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = ADD_TEMPORADA
        )
    }
}