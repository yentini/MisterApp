package com.example.misterapp.domain.usecases.temporada

import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.TemporadaModel
import javax.inject.Inject

class GetNumTemporadaUseCase @Inject constructor(
    private val temporadaRepository: TemporadaRepository
){
    operator fun invoke(): Int{
        return temporadaRepository.getNumTemporadas()
    }
}