package com.example.misterapp.ui.players

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.usecases.player.AddPlayerUseCase
import com.example.misterapp.domain.usecases.player.GetAllPlayersUseCase
import com.example.misterapp.domain.usecases.player.GetPlayerUseCase
import com.example.misterapp.domain.usecases.player.UpdatePlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class OnePlayerViewModel  @Inject constructor(
    private val getPlayerUseCase: GetPlayerUseCase,
    private val getAllPlayersUseCase: GetAllPlayersUseCase,
    private val updatePlayerUseCase: UpdatePlayerUseCase
): ViewModel() {

    private val _playerName = MutableLiveData<String>()
    val playerName : LiveData<String> = _playerName

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _phone = MutableLiveData<String>()
    val phone : LiveData<String> = _phone

    private val _birthday = MutableLiveData<String>()
    val birthday : LiveData<String> = _birthday

    private val _isAddingPlayerEnable = MutableLiveData<Boolean>()
    val isAddingPlayerEnable:LiveData<Boolean> = _isAddingPlayerEnable

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _playerName.value = ""
        _email.value = ""
        _phone.value = ""
        _birthday.value = ""
        _showDialog.value = true
    }

    fun onPlayerUpdated(
        playerName: String,
        email: String,
        phone: Int,
        birthday: LocalDate
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePlayerUseCase(PlayerModel(
                                name = playerName,
                                email = email,
                                phone = phone,
                                birthday = birthday
                            ))
        }
    }

    fun onAddinValuesChanged(playerName: String, email: String, phone: String, birthday: String){
        _playerName.value = playerName
        _email.value = email
        _phone.value = phone
        _birthday.value = birthday
        _isAddingPlayerEnable.value = enableAddingPlayer(playerName, email, phone)
    }

    private fun enableAddingPlayer(name: String, email: String, phone: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                                        !name.isEmpty() &&
                                        !phone.isEmpty()
}