package com.example.misterapp.ui.temporada

import com.example.misterapp.domain.model.TemporadaModel

sealed class TemporadasEvent {
    data class DeleteTemporada(val temporada: TemporadaModel): TemporadasEvent()
    data class UpdateTemporada(val temporada: TemporadaModel): TemporadasEvent()
}
