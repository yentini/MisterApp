package com.example.misterapp.ui.players.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.misterapp.R
import com.example.misterapp.core.Constants.Companion.ADD
import com.example.misterapp.core.Constants.Companion.ADD_PLAYER
import com.example.misterapp.core.Constants.Companion.BIRTHDAY
import com.example.misterapp.core.Constants.Companion.EMAIL
import com.example.misterapp.core.Constants.Companion.EMAIL_EXAMPLE
import com.example.misterapp.core.Constants.Companion.NO_VALUE
import com.example.misterapp.core.Constants.Companion.PHONE
import com.example.misterapp.core.Constants.Companion.PLAYER_NAME
import com.example.misterapp.ui.players.PlayersViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun AddPlayerAlertDialog(
    playersViewModel: PlayersViewModel = hiltViewModel(),
) {
    val showDialog: Boolean by playersViewModel.showDialog.observeAsState(false)

    if (showDialog) {
        val isAddingPlayerEnable: Boolean by playersViewModel.isAddingPlayerEnable.observeAsState(initial = false)
        val playerName by playersViewModel.playerName.observeAsState(NO_VALUE)
        val email by playersViewModel.email.observeAsState(NO_VALUE)
        val phone by playersViewModel.phone.observeAsState(NO_VALUE)
        val birthday by playersViewModel.birthday.observeAsState(NO_VALUE)
        var mybirthday by rememberSaveable { mutableStateOf(NO_VALUE) }
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
        Dialog(
            onDismissRequest = { playersViewModel.onDialogClose() }
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = ADD_PLAYER,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
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
                        birthday = birthday) },
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
                                        birthday = birthday) },
                    label = { Text(text = EMAIL) },
                    placeholder = { Text(text = EMAIL_EXAMPLE) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

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
                                        birthday = birthday) },
                    label = { Text(text = PHONE) },
                    placeholder = { Text(text = PHONE) },
                    keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.Number)

                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
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
                        )}}
                    )
                }                
                Spacer(modifier = Modifier.size(4.dp))
                Button(onClick = {
                        playersViewModel.onDialogClose()
                        playersViewModel.onPlayerAdded(playerName, email, phone.toInt(), getBirthday(mybirthday))
                     }, modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                        enabled = isAddingPlayerEnable
                ) {
                    Text(text = ADD)
                }
            }
        }
    }
}

fun getBirthday(date: String): LocalDate{
    val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
    try {
        val datetemp = LocalDate.parse(date, formatter)
        return datetemp
    }catch (e: Exception){
        return LocalDate.now()
    }
}