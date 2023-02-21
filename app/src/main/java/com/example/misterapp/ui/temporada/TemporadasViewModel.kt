package com.example.misterapp.ui.temporada

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
    private val getNumTemporadaUseCase: GetNumTemporadaUseCase,
    private val deleteTemporadaUseCase: DeleteTemporadaUseCase,
    private val updateTemporadaUseCase: UpdateTemporadaUseCase,
    private val updateTemporadasUseCase: UpdateTemporadasUseCase,
    private val getTemporadaFavoriteUseCase: GetTemporadaFavoriteUseCase,
    private val getTemporadaUseCase: GetTemporadaUseCase
): ViewModel(){

    val uiState: StateFlow<TemporadasUiState> = getTemporadasUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    var uiModifyTemporadaState: StateFlow<ModifyTemporadaUiState> =
        getTemporadaUseCase(-1).map(ModifyTemporadaUiState::Success)
        .catch { ModifyTemporadaUiState.Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ModifyTemporadaUiState.Loading
        )

    fun refresUiOneTemporadaState(temporadaId: Int){
        uiModifyTemporadaState =
            getTemporadaUseCase(temporadaId).map(ModifyTemporadaUiState::Success)
            .catch { ModifyTemporadaUiState.Error(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                ModifyTemporadaUiState.Loading
            )
    }

    private val _numTemporadas: MutableStateFlow<Int> = MutableStateFlow(0)
    val numTemporadas = _numTemporadas

    private val _temporadaFavorite: MutableStateFlow<TemporadaModel> = MutableStateFlow(TemporadaModel(name = NO_VALUE, favorite = false))
    val temporadaFavorite: StateFlow<TemporadaModel> = _temporadaFavorite

    private val _temporadaName = MutableLiveData<String>()
    val temporadaName : LiveData<String> = _temporadaName

    private val _isAddingTemporadaEnable = MutableLiveData<Boolean>()
    val isAddingTemporadaEnable:LiveData<Boolean> = _isAddingTemporadaEnable

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    init {
        viewModelScope.launch {
            getTemporadaFavorite()
            getNumTemporadas()
        }
    }

    fun onEvent(event: TemporadasEvent) {
        when (event) {
            is TemporadasEvent.DeleteTemporada -> {
                viewModelScope.launch {
                    deleteTemporadaUseCase(event.temporada)
                }
            }
            is TemporadasEvent.UpdateTemporada -> {
                viewModelScope.launch {
                    updateTemporadaUseCase(event.temporada)
                }
            }
        }
    }

    fun onAddinValuesChanged(temporadaName: String){
        _temporadaName.value = temporadaName
        _isAddingTemporadaEnable.value = enableAddingTemporada(temporadaName)
    }

    private fun enableAddingTemporada(name: String) = name.length > 3

    private fun getTemporadaFavorite(){
        viewModelScope.launch {
            getTemporadaFavoriteUseCase()
                .collect { temporada ->
                    _temporadaFavorite.value =  _temporadaFavorite.value.copy(
                        id = temporada.id,
                        name = temporada.name,
                        favorite = temporada.favorite
                    )
                }
        }
    }

    fun getNumTemporadas(){
        viewModelScope.launch(Dispatchers.IO) {
            getNumTemporadaUseCase().collect{ _numTemporadas.value = it}
        }
    }

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTemporadaCreated(
        temporadaName: String,
        favorite: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addTemporadaUseCase(TemporadaModel(name = temporadaName, favorite = favorite))
        }
    }

    fun deleteTemporada(temporada: TemporadaModel){
        viewModelScope.launch(Dispatchers.IO) {
            deleteTemporadaUseCase(temporada)
        }
    }

    fun updateTemporadas(temporadas: List<TemporadaModel>){
        viewModelScope.launch(Dispatchers.IO) {
            updateTemporadasUseCase(temporadas)
        }
    }

    fun getTemporada(temporadaId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            getTemporadaUseCase(temporadaId)
        }
    }
}