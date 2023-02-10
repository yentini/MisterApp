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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel  @Inject constructor(
    private val getAllPlayersUseCase: GetAllPlayersUseCase,
    private val getPlayerUseCase: GetPlayerUseCase,
    private val addPlayerUseCase: AddPlayerUseCase
): ViewModel() {
    val uiState: StateFlow<PlayersUiState> = getAllPlayersUseCase().map(PlayersUiState::Success)
        .catch { PlayersUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PlayersUiState.Loading)

    var uiOnePlayerState: StateFlow<PlayerUiState> = getPlayerUseCase(-1).map(PlayerUiState::Success)
        .catch { PlayerUiState.Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PlayerUiState.Loading
        )

    fun refresUiState(playerId: Int){
        uiOnePlayerState = getPlayerUseCase(playerId).map(PlayerUiState::Success)
            .catch { PlayerUiState.Error(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                PlayerUiState.Loading
            )
    }

    private val _nameOrderAsc = MutableLiveData<Boolean>(true)
    val nameOrderAsc : LiveData<Boolean> = _nameOrderAsc

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

    fun toggleNameOrder(){
        _nameOrderAsc.value = !_nameOrderAsc.value!!
    }

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

    fun onPlayerAdded(
        playerName: String,
        email: String,
        phone: Int,
        birthday: LocalDate
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addPlayerUseCase(PlayerModel(
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