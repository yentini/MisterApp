package com.example.misterapp.domain.usecases.teamplayers

import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.data.repository.TeamPlayersRepository
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamPlayerModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetTeamPlayersUseCase @Inject constructor(
        private val teamPlayersRepository: TeamPlayersRepository
) {
    operator fun invoke(teamId: Int): Flow<List<PlayerModel>> {
        return teamPlayersRepository.getTeamPlayers(teamId)
    }
}