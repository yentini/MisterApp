package com.example.misterapp.data.repository

import com.example.misterapp.data.TeamPlayersEntity
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.model.TeamPlayerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamPlayersRepository @Inject constructor(private val teamPlayersDao: TeamPlayersDao) {

    fun getTeamPlayers(teamId: Int): Flow<List<PlayerModel>> {
        return teamPlayersDao.getTeamPlayers(teamId)
            .map {players -> players.teamPlayers.map {
                    PlayerModel(
                        it.playerId,
                        it.name,
                        it.email,
                        it.phone,
                        it.birthday,
                        number = players.teamPlayersRef.filter {playerRef -> playerRef.playerId == it.playerId }.first().number
                    )
                }
            }
    }

    suspend fun add(teamPlayers: List<TeamPlayerModel>) {
        teamPlayersDao.addPlayersToTeam(
            teamPlayers.map { it.toData() }
        )
    }

    suspend fun delete(teamPlayer: TeamPlayerModel) {
        teamPlayersDao.deletePlayerTeam(teamPlayer.toData())
    }

    suspend fun update(teamPlayer: TeamPlayerModel) {
        teamPlayersDao.updatePlayerTeam(teamPlayer.toData())
    }

}

fun TeamPlayerModel.toData(): TeamPlayersEntity {
    return TeamPlayersEntity(this.playerId, this.teamId, this.number)
}