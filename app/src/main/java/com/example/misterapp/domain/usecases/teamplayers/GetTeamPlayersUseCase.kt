package com.example.misterapp.domain.usecases.teamplayers

import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.data.repository.TeamPlayersRepository
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamPlayerModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetTeamPlayersUseCase @Inject constructor(
        private val teamPlayersRepository: TeamPlayersRepository,
        private val playersRepository: PlayerRepository
) {
    suspend operator fun invoke(teamId: Int): Flow<List<PlayerModel>> {

        val teamPlayersList: List<TeamPlayerModel> = teamPlayersRepository.getTeamPlayers(teamId).first()
        val players: List<PlayerModel> = playersRepository.players.first()
        val teamPlayers: List<PlayerModel> = players.filter {player -> teamPlayersList.any { player.playerId == it.playerId } }

        return flowOf(teamPlayers)
    }
}