package com.example.misterapp.domain.usecases.temporada

import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.TemporadaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNumTemporadaUseCase @Inject constructor(
    private val temporadaRepository: TemporadaRepository
){
    operator fun invoke(): Flow<Int> {
        return temporadaRepository.getNumTemporadas()
    }
}