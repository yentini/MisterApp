package com.example.misterapp.ui.players

import com.example.misterapp.core.Order
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.util.PlayerOrder

data class PlayersState(
    val players: List<PlayerModel> = emptyList(),
    val playerOrder: PlayerOrder = PlayerOrder.Birthday(Order.DESC),
    val isOrderSectionVisible: Boolean = false
)
