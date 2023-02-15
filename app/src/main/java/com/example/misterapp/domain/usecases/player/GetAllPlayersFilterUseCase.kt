package com.example.misterapp.domain.usecases.player

import com.example.misterapp.core.Order
import com.example.misterapp.data.repository.PlayerRepository
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.util.PlayerOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllPlayersFilterUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    operator fun invoke(
        playerOrder: PlayerOrder = PlayerOrder.Birthday(Order.DESC)
    ): Flow<List<PlayerModel>> {
        return playerRepository.players.map { players ->
            when(playerOrder.orderType){
                Order.ASC -> {
                    when(playerOrder){
                        is PlayerOrder.Name-> players.sortedBy { it.name.lowercase() }
                        is PlayerOrder.Birthday-> players.sortedBy { it.birthday }
                    }
                }
                Order.DESC -> {
                    when(playerOrder){
                        is PlayerOrder.Name-> players.sortedByDescending { it.name.lowercase() }
                        is PlayerOrder.Birthday-> players.sortedByDescending { it.birthday }
                    }
                }
            }
        }
    }
}