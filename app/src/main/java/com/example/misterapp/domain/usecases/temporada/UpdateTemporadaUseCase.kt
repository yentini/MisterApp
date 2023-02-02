package com.example.misterapp.domain.usecases.temporada

import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.TemporadaModel
import javax.inject.Inject

class UpdateTemporadaUseCase@Inject constructor(
    private val temporadaRepository: TemporadaRepository
){
    suspend operator fun invoke(temporadaModel: TemporadaModel){
        temporadaRepository.update(temporadaModel)
    }
}