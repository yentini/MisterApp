package com.example.misterapp.ui.players

import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.util.PlayerOrder

sealed class PlayersEvent {
    data class Order(val playerOrder: PlayerOrder): PlayersEvent()
    data class DeletePlayer(val player: PlayerModel): PlayersEvent()
    object ToggleOrderSection: PlayersEvent()
}
