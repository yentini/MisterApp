package com.example.misterapp.ui.my_teams.components

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.misterapp.core.ActionItem
import com.example.misterapp.core.Constants.Companion.DELETE
import com.example.misterapp.core.Constants.Companion.EDIT
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.generic_components.ContentLoadingProgressBar
import com.example.misterapp.ui.generic_components.OverflowMenuAction
import com.example.misterapp.ui.temporada.TemporadasUiState
import com.example.misterapp.ui.temporada.TemporadasViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MyTeamsTopBar (
    temporadasViewModel: TemporadasViewModel = hiltViewModel(),
    temporada: TemporadaModel,
    navigateBack: () -> Unit,
    navigateTemporadas: () -> Unit,
    deleteTemporada: (TemporadaModel) -> Unit,
    navigateModifyTemporada: () -> Unit
){
    val options = myTeamsOptions(
        navigateTemporadas,
        deleteTemporada,
        temporada,
        temporadasViewModel.numTemporadas.value,
        navigateModifyTemporada
    )

    TopAppBar (
        title = {
            Text(temporada.name)
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector =  Icons.Outlined.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            val (isExpanded, setExpanded) = remember { mutableStateOf(false) }
            OverflowMenuAction(
                isExpanded,
                setExpanded,
                options
            )
        }
    )
}

private fun myTeamsOptions(
    navigateTemporadas: () -> Unit,
    deleteTemporada: (TemporadaModel) -> Unit,
    temporada: TemporadaModel,
    numTemporadas: Int,
    navigateModifyTemporada: () -> Unit
): List<ActionItem>{
    return listOf(
        ActionItem(
            name = EDIT,
            action = {
                navigateModifyTemporada()
            },
            order = 1
        ),
        ActionItem(
            name = DELETE,
            action = {
                deleteTemporada(temporada)
                navigateTemporadas()
            },
            order = 2,
            enabled = numTemporadas == 1 || !temporada.favorite
        )
    )
}