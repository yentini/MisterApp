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
    var isSelected: MutableState<Boolean> = mutableStateOf(false),
    val number: Int = 0
){
    companion object {
        fun NameComparator(asc: Boolean): Comparator<PlayerModel>{
            if (asc) return Comparator<PlayerModel> { left, right -> left.name.compareTo(right.name) }
            else return Comparator<PlayerModel> { left, right -> left.name.compareTo(right.name) }.reversed()
        }
    }

    fun toggle(){
        isSelected.value = !isSelected.value
    }
}