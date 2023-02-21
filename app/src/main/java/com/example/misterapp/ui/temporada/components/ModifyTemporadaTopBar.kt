package com.example.misterapp.ui.temporada.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import com.example.misterapp.domain.model.TemporadaModel

@Composable
fun ModifyTemporadaTopBar (
    temporada: TemporadaModel,
    navigateBack: () -> Unit
){
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
        }
    )
}