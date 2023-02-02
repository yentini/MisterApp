package com.example.misterapp.domain.usecases.player

import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.data.repository.TemporadaRepository
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TemporadaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayerUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
){
    operator fun invoke(id: Int): Flow<PlayerModel> {
        return playerRepository.get(id)
    }
}