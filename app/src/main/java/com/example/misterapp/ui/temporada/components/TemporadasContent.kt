package com.example.misterapp.ui.temporada.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun TemporadasContent(
    temporadaActualFavorite: TemporadaModel?,
    temporadas: List<TemporadaModel>,
    navigateToMyTeamsScreen: (temporadaId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                temporadas, key = { it.id }
            ) { temporada ->
                TemporadaCard(
                    temporadaActualFavorite,
                    temporada,
                    navigateToMyTeamsScreen
                )
            }
        }
    }
}

