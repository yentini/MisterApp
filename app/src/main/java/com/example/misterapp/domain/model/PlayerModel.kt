package com.example.misterapp.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.example.misterapp.core.Constants
import java.time.LocalDate

data class PlayerModel (
    val playerId: Int = System.currentTimeMillis().hashCode(),
    val name: String,
    val email: String,
    val phone: Int,
    val birthday: LocalDate,
    var isSelected: MutableState<Boolean> = mutableStateOf(false)
){
    fun toggle(){
        isSelected.value = !isSelected.value
    }
}