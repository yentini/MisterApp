package com.example.misterapp.ui.matches

import com.example.misterapp.domain.model.MatchModel
import com.example.misterapp.domain.model.PlayerModel
import com.example.misterapp.domain.util.MatchOrder
import com.example.misterapp.domain.util.PlayerOrder

sealed class MatchesEvent {
    data class GetTeam(val teamId: Int): MatchesEvent()
    data class Order(val teamId: Int, val matchOrder: MatchOrder): MatchesEvent()
    data class AddMatch(val match: MatchModel): MatchesEvent()
    data class DeleteMatch(val match: MatchModel): MatchesEvent()
    /*data class UpdateMatch(val match: MatchModel): MatchesEvent()*/
    object ToggleOrderSection: MatchesEvent()
}
