package com.example.misterapp.ui.temporada.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.domain.model.TeamModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.temporada.TemporadasViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TemporadaCard(
    temporadaActualFavorite: TemporadaModel?,
    temporadaModel: TemporadaModel,
    navigateToMyTeamsScreen: (temporadaId: Int) -> Unit,
    temporadasViewModel: TemporadasViewModel = hiltViewModel()
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = 4.dp,
        onClick ={
            navigateToMyTeamsScreen(temporadaModel.id)
        }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(temporadaModel.name)
            IconToggleButton(
                checked = temporadaModel.favorite,
                onCheckedChange = {
                    temporadasViewModel.updateTemporadas(
                        listOf<TemporadaModel>(temporadaActualFavorite!!.copy(favorite = false),
                                temporadaModel.copy(favorite = true))
                    )
                }
            ) {
                Icon(
                    tint = Color(0xffE91E63),
                    modifier = Modifier.graphicsLayer {
                        scaleX = 1.3f
                        scaleY = 1.3f
                    },
                    imageVector = if (temporadaModel.favorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = null
                )
            }
        }
    }
}