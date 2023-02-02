package com.example.misterapp.domain.usecases.player

import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPlayersUseCase @Inject constructor(private val playerRepository: PlayerRepository) {
    operator fun invoke(): Flow<List<PlayerModel>> = playerRepository.players
}