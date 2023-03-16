package com.example.misterapp.domain.util

import com.example.misterapp.core.Order

sealed class MatchOrder(val orderType: Order) {
    class Date(orderType: Order): MatchOrder(orderType)
    class Opponent(orderType: Order): MatchOrder(orderType)
    class Tournament(orderType: Order): MatchOrder(orderType)
    class Local(orderType: Order): MatchOrder(orderType)

    fun copy(orderType: Order): MatchOrder {
        return when(this) {
            is Date -> Date(orderType)
            is Opponent -> Opponent(orderType)
            is Tournament -> Tournament(orderType)
            is Local -> Local(orderType)
        }
    }
}