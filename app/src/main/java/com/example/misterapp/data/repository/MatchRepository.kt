package com.example.misterapp.data.repository

import com.example.misterapp.data.MatchEntity
import com.example.misterapp.domain.model.MatchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchRepository @Inject constructor(private val matchDao: MatchDao) {

    fun getAll(teamId: Int): Flow<List<MatchModel>> =
        matchDao.getAll(teamId).map {
                items -> items.map { MatchModel(it.matchId,
                                                it.teamId,
                                                it.date,
                                                it.opponent,
                                                it.tournament,
                                                it.local
                                    )}}

    suspend fun add(match: MatchModel){
        matchDao.addMatch(match.toData())
    }

    suspend fun delete(match: MatchModel){
        matchDao.deleteMatch(match.toData())
    }

    suspend fun update(match: MatchModel){
        matchDao.updateMatch(match.toData())
    }

    fun get(id: Int): Flow<MatchModel>{
        return matchDao.getMatch(id).map {
                                                    MatchModel(it.matchId,
                                                        it.teamId,
                                                        it.date,
                                                        it.opponent,
                                                        it.tournament,
                                                        it.local
                                                    )
                                                }
    }

}

fun MatchModel.toData(): MatchEntity{
    return MatchEntity(this.matchId, this.teamId, this.date, this.opponent, this.tournament, this.local)
}