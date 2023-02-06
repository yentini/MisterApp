package com.example.misterapp.data.repository

import com.example.misterapp.data.TeamPlayersEntity
import com.example.misterapp.domain.model.TeamPlayerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamPlayersRepository @Inject constructor(private val teamPlayersDao: TeamPlayersDao) {

    fun getTeamPlayers(teamId: Int): Flow<List<TeamPlayerModel>> {
        return teamPlayersDao.getTeamPlayers(teamId)
            .map { items ->
                items.map {
                    TeamPlayerModel(
                        it.teamId,
                        it.teamId,
                        it.number
                    )
                }
            }
    }

    suspend fun add(teamPlayer: TeamPlayerModel) {
        teamPlayersDao.addPlayerToTeam(teamPlayer.toData())
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