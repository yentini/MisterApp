package com.example.misterapp.domain.usecases.teamplayers

import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.data.repository.TeamPlayersRepository
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamPlayerModel
import javax.inject.Inject

class AddPlayersToTeamUseCase @Inject constructor(
    private val teamPlayersRepository: TeamPlayersRepository
){
    suspend operator fun invoke(teamPlayers: List<TeamPlayerModel>){
        teamPlayersRepository.add(teamPlayers)
    }
}