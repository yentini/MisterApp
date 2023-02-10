package com.example.misterapp.ui.temporada.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.core.Constants.Companion.ADD
import com.example.misterapp.core.Constants.Companion.ADD_TEMPORADA
import com.example.misterapp.core.Constants.Companion.NO_VALUE
import com.example.misterapp.core.Constants.Companion.TEMPORADA
import com.example.misterapp.core.Constants.Companion.TEMPORADA_EXAMPLE
import com.example.misterapp.core.Constants.Companion.TEMPORADA_YEAR
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun AddTemporadaAlertDialog(
    temporadasViewModel: TemporadasViewModel = hiltViewModel(),
    show: Boolean,
    onDismiss: () -> Unit,
    onTemporadaAdded: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit){
        temporadasViewModel.getNumTemporadas()
    }

    if (show) {
        var myTemporada by remember { mutableStateOf(NO_VALUE) }

        Dialog(
            onDismissRequest = { onDismiss() }
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = ADD_TEMPORADA,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .fillMaxWidth(),
                    value = myTemporada,
                    onValueChange = { myTemporada = it },
                    label = { Text(text = TEMPORADA) },
                    placeholder = { Text(text = TEMPORADA_EXAMPLE) }

                )
                Spacer(modifier = Modifier.size(4.dp))
                Button(onClick = {
                    onDismiss()
                    onTemporadaAdded(myTemporada, temporadasViewModel.numTemporadas == 0)
                    myTemporada = ""
                }, modifier = Modifier
                        .padding(horizontal =  8.dp)
                        .fillMaxWidth()) {
                    Text(text = ADD)
                }
            }
        }
    }
}