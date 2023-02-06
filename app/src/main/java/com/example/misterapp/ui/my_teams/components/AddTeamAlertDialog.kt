package com.example.misterapp.ui.my_teams.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.misterapp.core.Categorys
import com.example.misterapp.core.Constants.Companion.ADD
import com.example.misterapp.core.Constants.Companion.ADD_TEAM
import com.example.misterapp.core.Constants.Companion.CATEGORY
import com.example.misterapp.core.Constants.Companion.CLUB
import com.example.misterapp.core.Constants.Companion.LEVEL
import com.example.misterapp.core.Constants.Companion.NO_VALUE
import com.example.misterapp.core.Constants.Companion.YEAR
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddTeamAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onTeamAdded: (Int, String, String, Int, String) -> Unit,
    temporadaId: Int
) {
    if (show) {
        var level by remember { mutableStateOf(NO_VALUE) }
        var year by remember { mutableStateOf(NO_VALUE) }
        var club by remember { mutableStateOf(NO_VALUE) }
        val categorys = Categorys.values().map { it.description }
        val default = 0
        var expanded by remember { mutableStateOf(false) }
        var category by remember { mutableStateOf(categorys[default].toString()) }

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
                    text = ADD_TEAM,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = category,
                        onValueChange = { },
                        label = {
                            Text(CATEGORY)
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        categorys.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    category = selectionOption.toString()
                                    expanded = false
                                }
                            ) {
                                Text(text = selectionOption.toString())
                            }
                        }
                    }
                }
                OutlinedTextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    value = level,
                    label = { Text(LEVEL) },
                    onValueChange = { level = it }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    value = year.toString(),
                    label = { Text(YEAR) },
                    keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Number),
                    onValueChange = { year = it }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    value = club,
                    label = {
                        Text(CLUB)
                    },
                    onValueChange = { club = it }
                )
                Spacer(modifier = Modifier.size(4.dp))
                Button(onClick = {
                    onDismiss()
                    onTeamAdded(temporadaId, category, level, year.toInt(), club)
                    category = NO_VALUE
                    level = NO_VALUE
                    year = NO_VALUE
                    club = NO_VALUE
                }, modifier = Modifier
                        .padding(horizontal =  8.dp)
                        .fillMaxWidth()) {
                    Text(text = ADD)
                }
            }
        }
    }
}