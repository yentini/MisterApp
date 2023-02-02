package com.example.misterapp.ui.temporada

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misterapp.core.Constants.Companion.NO_VALUE
import com.example.misterapp.domain.model.TemporadaModel
import com.example.misterapp.domain.usecases.temporada.*
import com.example.misterapp.ui.temporada.TemporadasUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemporadasViewModel @Inject constructor(
    private val addTemporadaUseCase: AddTemporadaUseCase,
    private val getTemporadasUseCase: GetTemporadasUseCase,
    private val getTemporadaUseCase: GetTemporadaUseCase,
    private val deleteTemporadaUseCase: DeleteTemporadaUseCase,
    private val updateTemporadaUseCase: UpdateTemporadaUseCase
): ViewModel(){

    val uiState: StateFlow<TemporadasUiState> = getTemporadasUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTemporadaCreated(temporadaName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addTemporadaUseCase(TemporadaModel(name = temporadaName))
        }
    }

    fun deleteTemporada(temporada: TemporadaModel){
        viewModelScope.launch(Dispatchers.IO) {
            deleteTemporadaUseCase(temporada)
        }
    }

    fun updateTemporada(temporada: TemporadaModel){
        viewModelScope.launch(Dispatchers.IO) {
            updateTemporadaUseCase(temporada)
        }
    }

}