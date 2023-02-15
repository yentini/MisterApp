package com.example.misterapp.ui.players.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.R
import com.example.misterapp.core.Constants
import com.example.misterapp.core.Constants.Companion.BIRTHDAY
import com.example.misterapp.core.Constants.Companion.EMAIL
import com.example.misterapp.core.Constants.Companion.EMAIL_EXAMPLE
import com.example.misterapp.core.Constants.Companion.PHONE
import com.example.misterapp.core.Constants.Companion.PLAYER_NAME
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.ui.players.PlayersEvent
import com.example.misterapp.ui.players.PlayersViewModel
import com.example.misterapp.ui.temporada.TemporadasViewModel
import java.util.*

@Composable
fun OnePlayerContent(
    player: PlayerModel,
    playersViewModel: PlayersViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val isAddingPlayerEnable: Boolean by playersViewModel.isAddingPlayerEnable.observeAsState(initial = false)
    val playerName by playersViewModel.playerName.observeAsState(Constants.NO_VALUE)
    val phone by playersViewModel.phone.observeAsState(Constants.NO_VALUE)
    val email by playersViewModel.email.observeAsState(Constants.NO_VALUE)
    val birthday by playersViewModel.birthday.observeAsState(Constants.NO_VALUE)
    var mybirthday by rememberSaveable { mutableStateOf(Constants.NO_VALUE) }

    val mContext = LocalContext.current
    //CALENDAR
    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mybirthday = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )
    //CALENDAR

    LaunchedEffect(Unit){
        playersViewModel.onAddinValuesChanged(
            email = player.email,
            playerName = player.name,
            phone = player.phone.toString(),
            birthday = player.birthday.toString()
        )
        mybirthday = player.birthday.toString()
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
                                    birthday = birthday
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
                birthday = birthday
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
                birthday = birthday
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
            value = mybirthday,
            onValueChange = { playersViewModel.onAddinValuesChanged(
                email = email,
                playerName = playerName,
                phone = phone,
                birthday = mybirthday) },
            label = { Text(text = BIRTHDAY) },
            placeholder = { Text(text = BIRTHDAY) },
            readOnly = true,
            trailingIcon = { IconButton(onClick = {
                mDatePickerDialog.show()
            }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = null
                    )
                }
            }
        )
        Spacer(modifier = Modifier.size(4.dp))
        Button(onClick = {
            playersViewModel.onEvent(
                PlayersEvent.UpdatePlayer(player.copy(
                    name = playerName,
                    email = email,
                    phone = phone.toInt(),
                    birthday = getBirthday(mybirthday))
                )
            )
            navigateBack()
        }, modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
            enabled = isAddingPlayerEnable
        ) {
            Text(text = Constants.UPDATE)
        }
    }
}

