package com.example.misterapp.data.repository

import com.example.misterapp.data.TeamEntity
import com.example.misterapp.domain.model.TeamModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepository @Inject constructor(private val teamDao: TeamDao) {

    fun getTeams(): Flow<List<TeamModel>> {
        return teamDao.getAll()
            .map { items ->
                items.map {
                    TeamModel(
                        it.teamId,
                        it.temporadaId,
                        it.category,
                        it.level,
                        it.year,
                        it.club
                    )
                }
            }
    }

    suspend fun add(team: TeamModel) {
        teamDao.addTeam(team.toData())
    }

    suspend fun delete(team: TeamModel) {
        teamDao.deleteTeam(team.toData())
    }

    suspend fun update(team: TeamModel) {
        teamDao.updateTeam(team.toData())
    }

    fun get(teamId: Int): Flow<TeamModel> {
        return teamDao.getTeam(teamId).map {
            TeamModel(
                it.teamId,
                it.temporadaId,
                it.category,
                it.level,
                it.year,
                it.club
            )
        }
    }
}

fun TeamModel.toData(): TeamEntity {
    return TeamEntity(this.teamId, this.temporadaId, this.category, this.level, this.year, this.club)
}