package com.example.misterapp.ui.players.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.core.Constants
import com.example.misterapp.core.Constants.Companion.BIRTHDAY
import com.example.misterapp.core.Constants.Companion.EMAIL
import com.example.misterapp.core.Constants.Companion.EMAIL_EXAMPLE
import com.example.misterapp.core.Constants.Companion.PHONE
import com.example.misterapp.core.Constants.Companion.PLAYER_NAME
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.players.PlayersViewModel
import com.example.misterapp.ui.temporada.TemporadasViewModel

@Composable
fun OnePlayerContent(
    player: PlayerModel,
    playersViewModel: PlayersViewModel = hiltViewModel(),
) {
    val playerName by playersViewModel.playerName.observeAsState(Constants.NO_VALUE)
    val phone by playersViewModel.phone.observeAsState(Constants.NO_VALUE)
    val email by playersViewModel.email.observeAsState(Constants.NO_VALUE)

    LaunchedEffect(Unit){
        playersViewModel.onAddinValuesChanged(
            email = player.email,
            playerName = player.name,
            phone = player.phone.toString(),
            birthday = player.birthday.toString()
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
            value = playerName,
            onValueChange = { playersViewModel.onAddinValuesChanged(
                                    email = email,
                                    playerName = it,
                                    phone = phone,
                                    birthday = ""
                                )
            },
            label = { Text(text = PLAYER_NAME) },
            placeholder = { Text(text = PLAYER_NAME) }
        )
        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = email,
            onValueChange = { playersViewModel.onAddinValuesChanged(
                email = it,
                playerName = playerName,
                phone = phone,
                birthday = ""
            )
            },
            label = { Text(text = EMAIL) },
            placeholder = { Text(text = EMAIL_EXAMPLE) }
        )
        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = phone,
            onValueChange = { playersViewModel.onAddinValuesChanged(
                email = email,
                playerName = playerName,
                phone = it,
                birthday = ""
            )
            },
            label = { Text(text = PHONE) },
            placeholder = { Text(text = PHONE) }
        )
        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = playerName,
            onValueChange = { playersViewModel.onAddinValuesChanged(
                email = email,
                playerName = playerName,
                phone = phone,
                birthday = ""
            )
            },
            label = { Text(text = BIRTHDAY) },
            placeholder = { Text(text = BIRTHDAY) }
        )
    }
}

