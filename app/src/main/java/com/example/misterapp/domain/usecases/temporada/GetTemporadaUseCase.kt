package com.example.misterapp.domain.usecases.temporada

import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.TemporadaModel
import javax.inject.Inject

class GetTemporadaUseCase @Inject constructor(
    private val temporadaRepository: TemporadaRepository
){
    suspend operator fun invoke(id: Int): TemporadaModel{
        return temporadaRepository.get(id)
    }
}