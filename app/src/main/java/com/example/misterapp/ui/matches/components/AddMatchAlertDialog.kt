package com.example.misterapp.ui.matches.components

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
import com.example.misterapp.core.Constants.Companion.ADD_MATCH
import com.example.misterapp.core.Constants.Companion.ADD_PLAYER
import com.example.misterapp.core.Constants.Companion.BIRTHDAY
import com.example.misterapp.core.Constants.Companion.DATE
import com.example.misterapp.core.Constants.Companion.EMAIL
import com.example.misterapp.core.Constants.Companion.EMAIL_EXAMPLE
import com.example.misterapp.core.Constants.Companion.NO_VALUE
import com.example.misterapp.core.Constants.Companion.OPPONENT
import com.example.misterapp.core.Constants.Companion.PHONE
import com.example.misterapp.core.Constants.Companion.PLAYER_NAME
import com.example.misterapp.core.Constants.Companion.TOURNAMENT
import com.example.misterapp.domain.model.MatchModel
import com.example.misterapp.domain.util.PlayerOrder
import com.example.misterapp.ui.matches.MatchesEvent
import com.example.misterapp.ui.matches.MatchesViewModel
import com.example.misterapp.ui.players.PlayersViewModel
import com.example.misterapp.ui.players.components.DefaultRadioButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun AddMatchAlertDialog(
    matchesViewModel: MatchesViewModel = hiltViewModel(),
    teamId: Int
) {
    val showDialog: Boolean by matchesViewModel.showDialog.observeAsState(false)

    if (showDialog) {
        val isAddingPlayerEnable: Boolean by matchesViewModel.isAddingMatchEnable.observeAsState(initial = false)
        val opponent by matchesViewModel.opponent.observeAsState(NO_VALUE)
        val tournament by matchesViewModel.tournament.observeAsState(NO_VALUE)
        val local by matchesViewModel.local.observeAsState(false)
        val date by matchesViewModel.date.observeAsState(NO_VALUE)
        var myDate by rememberSaveable { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) }
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
                myDate = "$mDayOfMonth/${mMonth+1}/$mYear"
            }, mYear, mMonth, mDay
        )
        //CALENDAR
        Dialog(
            onDismissRequest = { matchesViewModel.onDialogClose() }
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = ADD_MATCH,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = myDate,
                        onValueChange = { matchesViewModel.onAddinValuesChanged(
                            opponent = opponent,
                            date = myDate,
                            tournament = tournament,
                            local = local) },
                        label = { Text(text = DATE) },
                        placeholder = { Text(text = DATE) },
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
                OutlinedTextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    value = opponent,
                    onValueChange = { matchesViewModel.onAddinValuesChanged(
                        opponent = it,
                        date = date,
                        tournament = tournament,
                        local = local) },
                    label = { Text(text = OPPONENT) },
                    placeholder = { Text(text = OPPONENT) }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    value = tournament,
                    onValueChange = { matchesViewModel.onAddinValuesChanged(
                        opponent = opponent,
                        date = date,
                        tournament = it,
                        local = local) },
                    label = { Text(text = TOURNAMENT) },
                    placeholder = { Text(text = TOURNAMENT) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DefaultRadioButton(
                        text = "Local",
                        selected = local,
                        onSelect = { matchesViewModel.onAddinValuesChanged(
                            opponent = opponent,
                            date = date,
                            tournament = tournament,
                            local = !local)  }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DefaultRadioButton(
                        text = "Visitante",
                        selected = !local,
                        onSelect = { matchesViewModel.onAddinValuesChanged(
                            opponent = opponent,
                            date = date,
                            tournament = tournament,
                            local = !local) }
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                Button(onClick = {
                        matchesViewModel.onDialogClose()
                        matchesViewModel.onEvent(MatchesEvent.AddMatch(MatchModel(teamId=teamId, date=getDate(myDate), opponent=opponent, tournament=tournament, local=local)))
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

fun getDate(date: String): LocalDate{
    val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
    try {
        val datetemp = LocalDate.parse(date, formatter)
        return datetemp
    }catch (e: Exception){
        return LocalDate.now()
    }
}