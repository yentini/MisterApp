package com.example.misterapp.domain.usecases.match

import com.example.misterapp.core.Order
import com.example.misterapp.data.repository.MatchRepository
import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.domain.model.MatchModel
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.util.MatchOrder
import com.example.misterapp.domain.util.PlayerOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMatchesFilterUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    operator fun invoke(
        teamId: Int,
        matchOrder: MatchOrder = MatchOrder.Date(Order.DESC)
    ): Flow<List<MatchModel>> {
        return matchRepository.getAll(teamId).map { matches ->
            when(matchOrder.orderType){
                Order.ASC -> {
                    when(matchOrder){
                        is MatchOrder.Date-> matches.sortedBy { it.date }
                        is MatchOrder.Local-> matches.sortedBy { it.local }
                        is MatchOrder.Opponent-> matches.sortedBy { it.opponent }
                        is MatchOrder.Tournament-> matches.sortedBy { it.tournament }
                    }
                }
                Order.DESC -> {
                    when(matchOrder){
                        is MatchOrder.Date-> matches.sortedByDescending { it.date }
                        is MatchOrder.Local-> matches.sortedByDescending { it.local }
                        is MatchOrder.Opponent-> matches.sortedByDescending { it.opponent }
                        is MatchOrder.Tournament-> matches.sortedByDescending { it.tournament }
                    }
                }
            }
        }
    }
}