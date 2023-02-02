package com.example.misterapp.ui.my_teams.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.misterapp.core.ActionItem
import com.example.misterapp.core.Constants.Companion.DELETE
import com.example.misterapp.core.Constants.Companion.EDIT
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.generic_components.OverflowMenuAction

@Composable
fun MyTeamsTopBar (
    temporada: TemporadaModel,
    navigateBack: () -> Unit,
    navigateTemporadas: () -> Unit,
    deleteTemporada: (TemporadaModel) -> Unit
){
    val options = myTeamsOptions(
        navigateTemporadas,
        deleteTemporada,
        temporada
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
    temporada: TemporadaModel
): List<ActionItem>{
    return listOf(
        ActionItem(
            name = EDIT,
            action = {
                // deleteTemporada(temporada)
                // navigateTemporadas()
            },
            order = 1
        ),
        ActionItem(
            name = DELETE,
            action = {
                deleteTemporada(temporada)
                navigateTemporadas()
            },
            order = 2
        )
    )
}