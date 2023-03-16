package com.example.misterapp.ui.matches

import com.example.misterapp.core.Order
import com.example.misterapp.domain.model.MatchModel
import com.example.misterapp.domain.util.MatchOrder

data class MatchesState(
    val matches: List<MatchModel> = emptyList(),
    val matchOrder: MatchOrder = MatchOrder.Date(Order.DESC),
    val isOrderSectionVisible: Boolean = false
)
