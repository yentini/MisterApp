package com.example.misterapp.domain.usecases.player

import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel
import javax.inject.Inject

class UpdatePlayerUseCase@Inject constructor(
    private val playerRepository: PlayerRepository
){
    suspend operator fun invoke(playerModel: PlayerModel){
        playerRepository.update(playerModel)
    }
}